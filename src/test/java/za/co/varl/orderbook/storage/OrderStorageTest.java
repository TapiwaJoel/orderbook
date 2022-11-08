package za.co.varl.orderbook.storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.varl.orderbook.OrderbookApplication;
import za.co.varl.orderbook.models.Order;
import za.co.varl.orderbook.utils.enums.Currency;

import static za.co.varl.orderbook.utils.TestUtils.buildOrder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderbookApplication.class)
@AutoConfigureMockMvc
@Slf4j
class OrderStorageTest {

    @Autowired
    OrderStorageService orderStorageService;

    @Test
    void addNewBuySide() {
        Order add = orderStorageService.add(buildOrder());
        Assertions.assertEquals(Currency.ZAR, add.getCurrency());
    }
}
