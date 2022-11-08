package za.co.varl.orderbook.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.storage.OrderStorageService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStorageService orderStorageService;

    @Override
    public ResponseEntity<ServiceResponse<Order>> save(Order order) {

        ServiceResponse<Order> serviceResponse = ServiceResponse.<Order>builder()
                .data(orderStorageService.add(order))
                .message("Order created successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Order>> edit(String id, OrderSide orderSide, Order order) {

        ServiceResponse<Order> serviceResponse = ServiceResponse.<Order>builder()
                .data(orderStorageService.edit(id, orderSide, order))
                .message("Order edit successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Map<OrderSide, List<Order>>>> search(OrderSide orderSide, OrderStatus orderStatus) {
        var serviceResponse = ServiceResponse.<Map<OrderSide, List<Order>>>builder()
                .data(orderStorageService.search(orderSide, orderStatus))
                .message("Orders retrieved successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }
}
