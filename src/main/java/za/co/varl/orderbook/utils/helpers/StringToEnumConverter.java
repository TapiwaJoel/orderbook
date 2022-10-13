package za.co.varl.orderbook.utils.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import za.co.varl.orderbook.utils.enums.SecurityType;

@Slf4j
@Component
public class StringToEnumConverter implements Converter<String, SecurityType> {
    @Override
    public SecurityType convert(String source) {
        try {
            log.info("HERE ALSO :::: {}", SecurityType.valueOf(source.toUpperCase()));
            return SecurityType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException thrown :::: {}", e);

            return null;
        }
    }
}
