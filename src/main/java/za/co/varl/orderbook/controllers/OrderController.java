package za.co.varl.orderbook.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.services.OrderService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ServiceResponse<Order>> save(@Valid @RequestBody Order order) {
        log.info("Response: [{}]", orderService.save(order));
        return orderService.save(order);
    }
}
