package za.co.varl.orderbook.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.enums.OrderSide;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderStore implements OrderStoreService {
    private final Map<OrderSide, List<Order>> orders = new LinkedHashMap<>();

    @Override
    public Order add(Order order) {

        // check if orders is empty
        List<Order> orderList = new ArrayList<>();
        var orderSide = order.getOrderSide();
        if (orders.containsKey(orderSide)) {
            orderList = orders.get(orderSide);
        }

        orderList.add(order);
        orders.put(orderSide, orderList);
        log.info("OLD ARRAY: [{}]", orderList);

        return order;
    }

    @Override
    public Order edit(OrderSide orderSide, Order order) {
        return null;
    }

    @Override
    public List<Order> search(OrderSide side, String fieldName, String value) {
        return null;
    }
}
