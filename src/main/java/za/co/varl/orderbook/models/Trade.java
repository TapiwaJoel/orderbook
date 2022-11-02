package za.co.varl.orderbook.models;

import lombok.*;
import za.co.varl.orderbook.utils.enums.Currency;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @Builder.Default
    private final String id = UUID.randomUUID().toString();

    private float quantity;
    private double price;
    private String security;
    private Currency currency;
    private String seller;
    private String buyer;

    @Builder.Default
    private final Date date = new Date();
}
