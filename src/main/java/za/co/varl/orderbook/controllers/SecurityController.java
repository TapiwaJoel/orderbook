package za.co.varl.orderbook.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.services.SecurityService;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/securities")
@Slf4j
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<ServiceResponse<Security>> save(@RequestBody Security security) {
        log.info("ADD SECURITY ::::::: [{}]", security.getId());
        return securityService.save(security);
    }

    @PutMapping("/{symbol}")
    public ResponseEntity<ServiceResponse<Security>> edit(@PathVariable String symbol, @RequestBody Security security) {
        log.info("Edit [{}] security ::::::: [{}]", symbol, security);
        return securityService.edit(symbol, security);
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<ServiceResponse<Security>> findBySymbol(@PathVariable String symbol) {
        log.info("Find security by symbol ::::::: [{}]", symbol);
        return securityService.findBySymbol(symbol);
    }

    @GetMapping()
    public ResponseEntity<ServiceResponse<List<Security>>> findAll() {
        log.info("Find all securities");
        return securityService.findAll();
    }
}
