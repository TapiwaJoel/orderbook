package za.co.varl.orderbook.models;

import lombok.*;
import za.co.varl.orderbook.utils.enums.Currency;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private OrderSide orderSide;
    private float quantity;
    private double price;

    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.PENDING;

    private Currency currency;

    private String security;

    private String trader;

    @Builder.Default
    private Date date = new Date();
}
