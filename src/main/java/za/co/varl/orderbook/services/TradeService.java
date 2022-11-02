package za.co.varl.orderbook.services;

import org.springframework.http.ResponseEntity;
import za.co.varl.orderbook.models.Trade;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

public interface TradeService {
    ResponseEntity<ServiceResponse<List<Trade>>> findAll();
}
