package za.co.varl.orderbook.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.storage.OrderStoreService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStoreService orderStoreService;

    @Override
    public ResponseEntity<ServiceResponse<Order>> save(Order order) {

        ServiceResponse<Order> serviceResponse = ServiceResponse.<Order>builder()
                .data(orderStoreService.add(order))
                .message("Order created successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Order>> edit(String id, OrderSide orderSide, Order order) {

        ServiceResponse<Order> serviceResponse = ServiceResponse.<Order>builder()
                .data(orderStoreService.edit(id, orderSide, order))
                .message("Order edit successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Map<OrderSide, List<Order>>>> search(OrderSide orderSide, OrderStatus orderStatus) {
        var serviceResponse = ServiceResponse.<Map<OrderSide, List<Order>>>builder()
                .data(orderStoreService.search(orderSide, orderStatus))
                .message("Orders retrieved successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }
}
