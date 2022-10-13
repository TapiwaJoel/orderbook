package za.co.varl.orderbook.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import za.co.varl.orderbook.models.Security;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class DataStorage {
    private  Map<String, Security> securities = new LinkedHashMap<>();
}
