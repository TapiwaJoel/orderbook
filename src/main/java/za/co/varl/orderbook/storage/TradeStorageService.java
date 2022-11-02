package za.co.varl.orderbook.storage;

import za.co.varl.orderbook.models.Trade;

import java.util.List;

public interface TradeStorageService {

    boolean add(Trade trade);

    List<Trade> findAll();
}
