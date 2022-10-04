package za.co.varl.orderbook.services;

import za.co.varl.orderbook.models.Trader;

import java.util.List;

public interface TraderService {
     Trader save(Trader trader);
     Trader edit(Trader trader);
     List<Trader> read();
}
