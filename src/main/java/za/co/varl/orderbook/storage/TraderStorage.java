package za.co.varl.orderbook.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.varl.orderbook.models.Trader;
import za.co.varl.orderbook.utils.exceptions.BadRequestException;
import za.co.varl.orderbook.utils.exceptions.ResourceConflictException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class TraderStorage implements TraderStorageService {

    private final Map<String, Trader> traders = new ConcurrentHashMap<>();

    @Override
    public Trader add(Trader trader) {

        log.info("Trader to be added::::[{}]", trader);
        // Validation
        if (trader.getEmail() == null || trader.getEmail().isBlank())
            throw new BadRequestException("Email field is required");

        if (trader.getFullName() == null || trader.getFullName().isBlank())
            throw new BadRequestException("FullName field is required");

        if (trader.getPhoneNumber() == null || trader.getPhoneNumber().isBlank())
            throw new BadRequestException("Phone number field is required");

        if (findByEmail(trader.getEmail()))
            throw new ResourceConflictException(String.format("Trader with email [%s] already exists", trader.getEmail()));

        traders.put(trader.getId(), trader);
        log.info("[{}] :: trader added. Total number of traders :: [{}] ", trader.getFullName(), traders.size());
        return trader;
    }

    @Override
    public Trader edit(String id, Trader trader) {

        if (id == null || id.isBlank())
            throw new BadRequestException("Id field is required");

        // get the security
        if (!traders.containsKey(id))
            throw new BadRequestException("Trader not found");

        var savedTrader = traders.get(id);

        if (savedTrader == null)
            throw new BadRequestException("Trader not found");

        // check if we have data to update
        if (trader.getEmail() != null && trader.getEmail().trim().length() > 0) {
            if (findByEmail(trader.getEmail()))
                throw new ResourceConflictException(String.format("Email [%s] already exists", trader.getEmail()));

            savedTrader.setEmail(trader.getEmail());
        }

        if (trader.getFullName() != null && trader.getFullName().trim().length() > 0)
            savedTrader.setFullName(trader.getFullName());

        if (trader.getPhoneNumber() != null && trader.getPhoneNumber().trim().length() > 0)
            savedTrader.setPhoneNumber(trader.getPhoneNumber());


        return traders.put(id, savedTrader);
    }

    @Override
    public Trader findById(String id) {
        if (id == null || id.isBlank())
            throw new BadRequestException("Id field is required");

        var findByIdTrader = traders.get(id);

        if (findByIdTrader == null)
            throw new BadRequestException("Trader supplied not found");

        return findByIdTrader;
    }

    @Override
    public List<Trader> findAll() {
        return traders.values().stream().toList();
    }

    boolean findByEmail(String email) {
        return traders.values().stream().anyMatch(trader -> trader.getEmail().equalsIgnoreCase(email));
    }
}
