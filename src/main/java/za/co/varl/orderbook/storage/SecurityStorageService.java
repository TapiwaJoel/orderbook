package za.co.varl.orderbook.storage;

import za.co.varl.orderbook.models.Security;

import java.util.List;

public interface SecurityStorageService {
    Security add(Security security);

    Security edit(String symbol, Security security);

    Security findBySymbol(String symbol);
    Security deleteBySymbol(String symbol);

    List<Security> findAll();
}
