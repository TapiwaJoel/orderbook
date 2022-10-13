package za.co.varl.orderbook.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.storage.SecurityStorage;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final SecurityStorage securityStorage;

    ServiceResponse<Security> serviceResponse;

    @Override
    public ResponseEntity<ServiceResponse<Security>> save(Security security) {
        serviceResponse = ServiceResponse.<Security>builder()
                .message("Security added successfully")
                .data(securityStorage.add(security))
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Security>> edit(String symbol, Security security) {

        serviceResponse = ServiceResponse.<Security>builder()
                .message("Security edited successfully")
                .data(securityStorage.edit(symbol, security))
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<Security>> findBySymbol(String symbol) {
        serviceResponse = ServiceResponse.<Security>builder()
                .message("Securities retrieved successfully")
                .data(securityStorage.findBySymbol(symbol))
                .build();

        return ResponseEntity.ok(serviceResponse);
    }

    @Override
    public ResponseEntity<ServiceResponse<List<Security>>> findAll() {

        ServiceResponse<List<Security>> serviceResponseList = ServiceResponse.<List<Security>>builder()
                .message("Securities retrieved successfully")
                .data(securityStorage.findAll())
                .build();

        return ResponseEntity.ok(serviceResponseList);
    }
}
