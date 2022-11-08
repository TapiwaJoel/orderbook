package za.co.varl.orderbook.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.varl.orderbook.models.Trader;
import za.co.varl.orderbook.services.TraderService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/traders")
@Slf4j
public class TraderController {

    private final TraderService traderService;

    @PostMapping
    public ResponseEntity<ServiceResponse<Trader>> save(@Valid @RequestBody Trader trader) {
        log.info("ADD TRADER ::::::: [{}]", trader);
        return traderService.save(trader);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<Trader>> edit(@PathVariable String id, @RequestBody Trader trader) {
        log.info("Edit [{}] trader ::::::: [{}]", id, trader);
        return traderService.edit(id, trader);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<Trader>> findById(@PathVariable String id) {
        log.info("Find trader by symbol ::::::: [{}]", id);
        return traderService.findById(id);
    }

    @GetMapping("")
    public ResponseEntity<ServiceResponse<List<Trader>>> findAll() {
        log.info("Find all traders");
        return traderService.findAll();
    }
}
