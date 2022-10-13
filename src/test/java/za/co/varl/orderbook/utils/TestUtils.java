package za.co.varl.orderbook.utils;

import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.utils.enums.*;

public class TestUtils {

    public static Order buildOrder(){
        return Order.builder()
                .orderSide(OrderSide.BUY)
                .orderStatus(OrderStatus.PENDING)
                .currency(Currency.ZAR)
                .security(buildSecurity())
                .quantity(20)
                .price(34.9)
                .id(1L)
                .build();
    }


    public static Security buildSecurity(){
        return Security.builder()
                .securityType(SecurityType.STOCK)
                .name("MTN Stock")
                .symbol("MTN")
                .balance(100000)
                .status(EntityStatus.ACTIVE)
                .id(1L)
                .build();
    }
}
