package za.co.varl.orderbook.utils.enums;

public enum SecurityType {
    STOCK("STOCK"),
    BOND("BOND"),
    CRYPTO("CRYPTO"),
    CURRENCY("CURRENCY");

    public final String type;

    SecurityType(String type) {
        this.type = type;
    }
}
