package za.co.varl.orderbook.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.varl.orderbook.models.Trade;
import za.co.varl.orderbook.services.TradeService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/trades")
@Slf4j
public class TradeController {

    private final TradeService tradeService;

    @GetMapping()
    public ResponseEntity<ServiceResponse<List<Trade>>> findAll() {
        log.info("Find all securities");
        return tradeService.findAll();
    }
}
