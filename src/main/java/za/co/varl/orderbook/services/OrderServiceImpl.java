package za.co.varl.orderbook.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.storage.OrderStoreService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderStoreService orderStoreService;

    @Override
    public ResponseEntity<ServiceResponse<Order>> save(Order order) {

        // Validations

        Order orderModel = mapper.map(order, Order.class);

        orderModel.setOrderStatus(OrderStatus.PENDING);
        orderModel.setDate(new Date());

        Order orderResponse = orderStoreService.add(orderModel);

        ServiceResponse<Order> serviceResponse = ServiceResponse.<Order>builder()
                .data(mapper.map(orderResponse, Order.class))
                .message("Order created successfully")
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Order>> edit(Order order) {
        return null;
    }

    @Override
    public ResponseEntity<ServiceResponse<List<Order>>> read() {
        return null;
    }
}
