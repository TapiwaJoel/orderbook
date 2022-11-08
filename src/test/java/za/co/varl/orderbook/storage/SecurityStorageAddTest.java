package za.co.varl.orderbook.storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.varl.orderbook.OrderbookApplication;
import za.co.varl.orderbook.models.Security;
import za.co.varl.orderbook.utils.exceptions.BadRequestException;
import za.co.varl.orderbook.utils.exceptions.ResourceConflictException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static za.co.varl.orderbook.utils.TestUtils.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderbookApplication.class)
@AutoConfigureMockMvc
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SecurityStorageAddTest {

    @Autowired
    SecurityStorageService securityStorageService;

    @DisplayName("add security validations")
    @Test
    @Order(1)
    void addSecurityValidations() {
        assertThrows(BadRequestException.class,
                () -> securityStorageService.add(buildSecurityMissingName()));

        assertThrows(BadRequestException.class,
                () -> securityStorageService.add(buildSecurityMissingType()));

        assertThrows(BadRequestException.class,
                () -> securityStorageService.add(buildSecurityMissingSymbol()));

        assertThrows(BadRequestException.class,
                () -> securityStorageService.add(buildSecurityMissingBalance()));
    }

    @DisplayName("Should successfully add security")
    @Test
    @Order(2)
    void addSecuritySuccess() {
        Security security = securityStorageService.add(buildSecurity());
        Assertions.assertEquals(buildSecurity().getSymbol(), security.getSymbol());
    }

    @DisplayName("Should not add security when symbol already exists")
    @Test
    @Order(3)
    void addSecurityExists() {
        assertThrows(ResourceConflictException.class,
                () -> securityStorageService.add(buildSecurity()));
    }

}
