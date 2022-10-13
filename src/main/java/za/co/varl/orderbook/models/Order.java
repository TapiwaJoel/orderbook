package za.co.varl.orderbook.models;

import lombok.*;
import za.co.varl.orderbook.utils.enums.Currency;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private OrderSide orderSide;
    private float quantity;
    private double price;
    private OrderStatus orderStatus;
    private Currency currency;
    private Security security;
    private Date date;
}
