package za.co.varl.orderbook.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Trade;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradeStorage implements TradeStorageService {

    private final List<Trade> trades = new CopyOnWriteArrayList<>();

    @Override
    public boolean add(Trade trade) {
        return trades.add(trade);
    }

    @Override
    public List<Trade> findAll() {
        return trades;
    }
}
