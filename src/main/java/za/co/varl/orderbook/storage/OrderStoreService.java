package za.co.varl.orderbook.storage;

import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.enums.OrderSide;

import java.util.List;

public interface OrderStoreService {
    Order add(Order order);

    Order edit(OrderSide orderSide, Order order);

    List<Order> search(OrderSide side, String fieldName, String value);
}
