package za.co.varl.orderbook.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Trader;
import za.co.varl.orderbook.storage.TraderStorage;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TraderServiceImpl implements TraderService {

    private final TraderStorage traderStorage;

    ServiceResponse<Trader> serviceResponse;

    @Override
    public ResponseEntity<ServiceResponse<Trader>> save(Trader trader) {

        serviceResponse = ServiceResponse.<Trader>builder()
                .message("Trader added successfully")
                .data(traderStorage.add(trader))
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Trader>> edit(String id, Trader trader) {
        serviceResponse = ServiceResponse.<Trader>builder()
                .message("Trader edited successfully")
                .data(traderStorage.edit(id, trader))
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Trader>> findById(String id) {
        serviceResponse = ServiceResponse.<Trader>builder()
                .message("Trader retrieved successfully")
                .data(traderStorage.findById(id))
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<List<Trader>>> findAll() {
        return null;
    }
}
