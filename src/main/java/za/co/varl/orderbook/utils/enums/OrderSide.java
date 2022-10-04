package za.co.varl.orderbook.utils.enums;

public enum OrderSide {
    BUY("BUY"), SELL("SELL");

    public final String side;

    OrderSide(String side) {
        this.side = side;
    }
}
