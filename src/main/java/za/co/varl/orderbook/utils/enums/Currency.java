package za.co.varl.orderbook.utils.enums;

public enum Currency {
    USD("USD"), ZAR("ZAR"), BTC("BTC");

    public final String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }
}
