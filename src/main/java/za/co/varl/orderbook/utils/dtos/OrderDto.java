package za.co.varl.orderbook.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String orderSide;
    private float quantity;
    private double price;
    private String orderStatus;
    private String currency;
    private String security;
}
