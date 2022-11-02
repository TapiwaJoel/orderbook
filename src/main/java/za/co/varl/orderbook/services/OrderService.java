package za.co.varl.orderbook.services;

import org.springframework.http.ResponseEntity;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface OrderService {

    ResponseEntity<ServiceResponse<Order>> save(Order order);

    ResponseEntity<ServiceResponse<Order>> edit(String id, OrderSide orderSide, Order order);

    ResponseEntity<ServiceResponse<Map<OrderSide, List<Order>>>> search(OrderSide orderSide, OrderStatus orderStatus);
}
