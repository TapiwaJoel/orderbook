package za.co.varl.orderbook.utils.enums;

public enum EntityStatus {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
    public final String status;

    EntityStatus(String status) {
        this.status = status;
    }
}
