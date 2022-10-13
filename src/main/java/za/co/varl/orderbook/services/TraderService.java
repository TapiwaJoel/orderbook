package za.co.varl.orderbook.services;

import org.springframework.http.ResponseEntity;
import za.co.varl.orderbook.models.Trader;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

public interface TraderService {
    ResponseEntity<ServiceResponse<Trader>> save(Trader trader);

    ResponseEntity<ServiceResponse<Trader>> edit(String id, Trader trader);
    ResponseEntity<ServiceResponse<Trader>> findById(String id);

    ResponseEntity<ServiceResponse<List<Trader>>> findAll();

}
