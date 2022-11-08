package za.co.varl.orderbook.utils;

import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.utils.enums.*;

import java.util.UUID;

public class TestUtils {

    public static Order buildOrder(){
        return Order.builder()
                .orderSide(OrderSide.BUY)
                .orderStatus(OrderStatus.PENDING)
                .currency(Currency.ZAR)
                .security(buildSecurity().getSymbol())
                .quantity(20)
                .price(34.9)
                .build();
    }


    public static Security buildSecurity(){
        return Security.builder()
                .securityType(SecurityType.STOCK)
                .name("MTN Stock")
                .symbol("MTN")
                .balance(100000)
                .status(EntityStatus.ACTIVE)
                .build();
    }

    public static Security buildSecurityEdit(){
        return Security.builder()
                .securityType(SecurityType.BOND)
                .name("MTN Bond")
                .symbol(buildSecurity().getSymbol())
                .balance(1000)
                .status(EntityStatus.INACTIVE)
                .build();
    }

    public static Security buildSecurityMissingSymbol(){
        return Security.builder()
                .securityType(SecurityType.STOCK)
                .name("MTN Stock")
                .balance(100000)
                .build();
    }

    public static Security buildSecurityMissingType(){
        return Security.builder()
                .name("MTN Stock")
                .symbol("MTN")
                .balance(100000)
                .build();
    }

    public static Security buildSecurityMissingBalance(){
        return Security.builder()
                .securityType(SecurityType.STOCK)
                .name("MTN Stock")
                .symbol("MTN")
                .build();
    }

    public static Security buildSecurityMissingName(){
        return Security.builder()
                .securityType(SecurityType.STOCK)
                .symbol("MTN")
                .balance(100000)
                .build();
    }
}
