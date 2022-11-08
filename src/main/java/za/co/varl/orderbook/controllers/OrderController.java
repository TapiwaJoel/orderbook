package za.co.varl.orderbook.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.services.OrderService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ServiceResponse<Order>> save(@Valid @RequestBody Order order) {
        log.info("ADD ORDER ::::::: [{}]", order.getId());
        return orderService.save(order);
    }

    @PutMapping("/{id}/{orderSide}")
    public ResponseEntity<ServiceResponse<Order>> edit(@PathVariable String id, @PathVariable OrderSide orderSide, @RequestBody Order order) {
        log.info("ADD ORDER ::::::: [{}]", order.getId());
        return orderService.edit(id, orderSide, order);
    }

    @GetMapping()
    public ResponseEntity<ServiceResponse<Map<OrderSide, List<Order>>>> search(@RequestParam(required = false, name = "side") OrderSide orderSide,
                                                                               @RequestParam(required = false, name = "status") OrderStatus orderStatus) {
        log.info("Searching orders ::: OrderSide => [{}], OrderStatus => [{}]", orderSide, orderStatus);
        return orderService.search(orderSide, orderStatus);
    }
}
