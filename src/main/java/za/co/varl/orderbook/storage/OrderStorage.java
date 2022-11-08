package za.co.varl.orderbook.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.models.Trade;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;
import za.co.varl.orderbook.utils.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableAsync
public class OrderStorage implements OrderStorageService {
    private final Map<OrderSide, List<Order>> orders = new ConcurrentHashMap<>();

    private final SecurityStorageService securityStorageService;

    private final TraderStorageService traderStorageService;

    private final TradeStorageService tradeStorageService;

    @Override
    public Order add(Order order) {

        log.info("Order to be added::::[{}]", order);

        if (order.getPrice() < 0)
            throw new BadRequestException("Price field is required");

        if (order.getQuantity() < 0)
            throw new BadRequestException("Quantity field is required");

        if (order.getSecurity() == null || order.getSecurity().isBlank())
            throw new BadRequestException("Security field is required");

        if (String.valueOf(order.getCurrency()) == null || String.valueOf(order.getSecurity()).isBlank())
            throw new BadRequestException("Currency field is required");

        if (String.valueOf(order.getOrderSide()) == null || String.valueOf(order.getOrderSide()).isBlank())
            throw new BadRequestException("OrderSide field is required");

        if (securityStorageService.findBySymbol(order.getSecurity()) == null)
            throw new BadRequestException("Security symbol supplied not found");

        if (traderStorageService.findById(order.getTrader()) == null)
            throw new BadRequestException("Trader supplied not found");

        // check if orders is empty
        var orderSide = order.getOrderSide();

        if (orders.containsKey(orderSide)) {
            orders.get(orderSide).add(order);
        } else {
            orders.put(orderSide, new ArrayList<>(List.of(order)));
        }

        log.info("OLD ARRAY: [{}]", orders.get(orderSide).size());

        return order;
    }

    @Override
    public Order edit(String id, OrderSide orderSide, Order order) {

        // Can only edit pending orders
        if (id == null || id.isBlank())
            throw new BadRequestException("Id is required");

        if (orderSide == null || String.valueOf(orderSide).isBlank())
            throw new BadRequestException("OrderSide is required");

        if (!orders.containsKey(orderSide))
            throw new BadRequestException("Order not found");

        var orderToBeEdited = orders.get(orderSide)
                .stream()
                .filter(order1 -> order1.getId().equalsIgnoreCase(id))
                .findFirst();

        if (orderToBeEdited.isEmpty())
            throw new BadRequestException("Order not found");

        var editOrder = orderToBeEdited.get();

        // check if order hasn't already matched
        if (editOrder.getOrderStatus().equals(OrderStatus.MATCHED))
            throw new BadRequestException("Order already matched");

        // check if status is matched
        if (order.getOrderStatus().equals(OrderStatus.MATCHED))
            throw new BadRequestException("Order cannot be matched manually");

        if (order.getOrderStatus() != null || !String.valueOf(order.getOrderStatus()).isBlank())
            editOrder.setOrderStatus(order.getOrderStatus());

        if (order.getOrderSide() != null || !String.valueOf(order.getOrderSide()).isBlank())
            editOrder.setOrderSide(order.getOrderSide());

        if (order.getPrice() > 0)
            editOrder.setPrice(order.getPrice());

        if (order.getQuantity() > 0)
            editOrder.setQuantity(order.getQuantity());

        if (order.getSecurity() != null || !order.getSecurity().isBlank()) {
            if (securityStorageService.findBySymbol(order.getSecurity()) == null)
                throw new BadRequestException("Security not found");

            editOrder.setSecurity(order.getSecurity());
        }

        if (order.getSecurity() != null || !order.getSecurity().isBlank())
            editOrder.setQuantity(order.getQuantity());

        // remove the old object
        orders.get(orderSide).removeIf(order1 -> order1.getId().equalsIgnoreCase(id));

        // add new object
        orders.get(orderSide).add(editOrder);

        log.info("order size: {}", orders.get(orderSide).size());

        return editOrder;
    }

    /**
     *
     */

    @Override
    public Map<OrderSide, List<Order>> search(OrderSide side, OrderStatus status) {
        Map<OrderSide, List<Order>> searchOrders = new ConcurrentHashMap<>();

        if (side == null && status == null)
            return orders;

        if (side != null && !side.side.isEmpty() && status == null) {
            searchOrders.put(side, orders.get(side));
            return searchOrders;
        }

        if (status != null && !status.status.isEmpty() && side == null) {

            var buyOrders = orders.get(OrderSide.BUY);

            if (buyOrders != null) {
                List<Order> buyOrderList = buyOrders
                        .stream()
                        .filter(buyOrder -> buyOrder.getOrderStatus().equals(status))
                        .toList();

                searchOrders.put(OrderSide.BUY, buyOrderList);
            }

            var sellOrders = orders.get(OrderSide.SELL);

            if (sellOrders != null) {
                List<Order> sellOrderList = sellOrders
                        .stream()
                        .filter(sellOrder -> sellOrder.getOrderStatus().equals(status))
                        .toList();

                searchOrders.put(OrderSide.SELL, sellOrderList);
            }

            return searchOrders;
        }

        var requiredOrders = orders.get(side).stream()
                .filter(requiredOrder -> requiredOrder.getOrderStatus().equals(status))
                .toList();

        searchOrders.put(side, requiredOrders);

        return searchOrders;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    @Override
    public void matchOrders() {
        log.info("**** Matching Orders *****");

        // search order book for unmatched

        var pendingOrders = search(null, OrderStatus.PENDING);
        var buyOrders = pendingOrders.get(OrderSide.BUY);
        var sellOrders = pendingOrders.get(OrderSide.SELL);

        if (buyOrders != null && sellOrders != null) {
            buyOrders.forEach(buyOrder -> sellOrders.forEach(sellOrder -> {

                // Matching on currency, quantity, security and price
                if (buyOrder.getSecurity().equals(sellOrder.getSecurity()) &&
                        buyOrder.getPrice() == sellOrder.getPrice() &&
                        buyOrder.getCurrency().equals(sellOrder.getCurrency()) &&
                        buyOrder.getQuantity() == sellOrder.getQuantity()) {

                    // UPDATING ORDER STATUSES
                    buyOrder.setOrderStatus(OrderStatus.MATCHED);
                    sellOrder.setOrderStatus(OrderStatus.MATCHED);

                    orders.get(OrderSide.BUY).removeIf(order1 -> order1.getId().equalsIgnoreCase(buyOrder.getId()));
                    orders.get(OrderSide.SELL).removeIf(order1 -> order1.getId().equalsIgnoreCase(sellOrder.getId()));

                    orders.get(OrderSide.BUY).add(buyOrder);
                    orders.get(OrderSide.SELL).add(sellOrder);

                    var trade = Trade.builder()
                            .price(buyOrder.getPrice())
                            .quantity(buyOrder.getQuantity())
                            .security(buyOrder.getSecurity())
                            .buyer(buyOrder.getTrader())
                            .seller(sellOrder.getTrader())
                            .currency(buyOrder.getCurrency())
                            .build();

                    // add trade service to trades
                    tradeStorageService.add(trade);
                }
            }));
        }
    }
}
