package za.co.varl.orderbook.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Trade;
import za.co.varl.orderbook.storage.TradeStorage;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final TradeStorage tradeStorage;
    ServiceResponse<List<Trade>> serviceResponse;

    @Override
    public ResponseEntity<ServiceResponse<List<Trade>>> findAll() {

        serviceResponse = ServiceResponse.<List<Trade>>builder()
                .message("Trader retrieved successfully")
                .data(tradeStorage.findAll())
                .build();
        return ResponseEntity.ok(serviceResponse);
    }
}
