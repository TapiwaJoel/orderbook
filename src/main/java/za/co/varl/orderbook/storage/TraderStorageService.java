package za.co.varl.orderbook.storage;

import za.co.varl.orderbook.models.Trader;

import java.util.List;

public interface TraderStorageService {
    Trader add(Trader trader);

    Trader edit(String symbol, Trader trader);

    Trader findById(String id);

    List<Trader> findAll();
}
