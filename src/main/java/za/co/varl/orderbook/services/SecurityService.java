package za.co.varl.orderbook.services;

import org.springframework.http.ResponseEntity;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

public interface SecurityService {
    ResponseEntity<ServiceResponse<Security>> save(Security security);

    ResponseEntity<ServiceResponse<Security>> edit(String symbol,Security security);

    ResponseEntity<ServiceResponse<Security>> findBySymbol(String symbol);

    ResponseEntity<ServiceResponse<List<Security>>> findAll();

}
