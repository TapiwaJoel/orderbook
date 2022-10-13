package za.co.varl.orderbook.storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.enums.Currency;

import static za.co.varl.orderbook.utils.TestUtils.buildOrder;

@SpringBootTest
@Slf4j
class OrderStoreTest {

    @Autowired
    OrderStore orderStore;


    @Test
    void addNewBuySide() {
        Order add = orderStore.add(buildOrder());
        Assertions.assertEquals(Currency.ZAR, add.getCurrency());
    }
}
