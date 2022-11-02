package za.co.varl.orderbook.storage;

import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface OrderStoreService {
    Order add(Order order);

    Order edit(String id, OrderSide orderSide, Order order);

    Map<OrderSide, List<Order>> search(OrderSide side, OrderStatus status);

    void matchOrders() ;
}
