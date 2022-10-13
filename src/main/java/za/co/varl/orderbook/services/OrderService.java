package za.co.varl.orderbook.services;

import org.springframework.http.ResponseEntity;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

public interface OrderService {

    ResponseEntity<ServiceResponse<Order>> save(Order order);
    ResponseEntity<ServiceResponse<Order>> edit(Order order);
    ResponseEntity<ServiceResponse<List<Order>>> read();
}
