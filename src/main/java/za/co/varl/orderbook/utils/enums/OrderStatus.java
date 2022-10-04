package za.co.varl.orderbook.utils.enums;

public enum OrderStatus {
    PENDING("PENDING"),
    MATCHED("MATCHED"),
    CANCELLED("CANCELLED");
    public final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
