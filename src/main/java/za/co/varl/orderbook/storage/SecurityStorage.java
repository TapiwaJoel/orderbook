package za.co.varl.orderbook.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.utils.exceptions.BadRequestException;
import za.co.varl.orderbook.utils.exceptions.ResourceConflictException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityStorage implements SecurityStorageService {

    private final Map<String, Security> securities = new ConcurrentHashMap<>();

    @Override
    public Security add(Security security) {

        log.info("Security to be added::::[{}]", security);
        // Validation
        if (security.getSymbol() == null || security.getSymbol().isBlank())
            throw new BadRequestException("Symbol field is required");

        if (security.getName() == null || security.getName().isBlank())
            throw new BadRequestException("Name field is required");

        if (String.valueOf(security.getSecurityType()) == null || String.valueOf(security.getSecurityType()).isBlank())
            throw new BadRequestException("SecurityType field is required");

        if (security.getBalance() < 0)
            throw new BadRequestException("Balance field is required");

        if (securities.containsKey(security.getSymbol()))
            throw new ResourceConflictException(String.format("Security with symbol [%s] already exists", security.getSymbol()));

        securities.put(security.getSymbol(), security);
        log.info("[{}] :: security added. Total number of securities :: [{}] ", security.getName(), securities.size());
        return security;
    }

    @Override
    public Security edit(String symbol, Security security) {

        // validations
        // symbols is necessary

        if (symbol == null || symbol.isBlank())
            throw new BadRequestException("Symbol field is required");

        // get the security
        if (!securities.containsKey(symbol))
            throw new BadRequestException("Security symbol supplied not found");

        var savedSecurity = securities.get(symbol);

        if (savedSecurity == null)
            throw new BadRequestException("Security symbol supplied not found");

        // check if we have data to update
        if (security.getName() != null && security.getName().trim().length() > 0)
            savedSecurity.setName(security.getName());

        if (security.getSecurityType() != null && !String.valueOf(security.getSecurityType()).isBlank())
            savedSecurity.setSecurityType(security.getSecurityType());

        if (security.getStatus() != null && !String.valueOf(security.getStatus()).isBlank())
            savedSecurity.setName(security.getName());

        if (security.getSymbol() != null && security.getSymbol().trim().length() > 0) {
            savedSecurity.setSymbol(security.getSymbol());
            symbol = security.getSymbol();
            // remove the old security as symbol had changed
            securities.remove(symbol);
        }

        return securities.put(symbol, savedSecurity);
    }

    @Override
    public Security findBySymbol(String symbol) {
        if (symbol == null || symbol.isBlank())
            throw new BadRequestException("Symbol field is required");

        var findBySymbol = securities.get(symbol);

        if (findBySymbol == null)
            throw new BadRequestException("Security symbol supplied not found");

        return findBySymbol;
    }

    @Override
    public List<Security> findAll() {
        return securities.values().stream().toList();
    }
}
