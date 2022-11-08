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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static za.co.varl.orderbook.utils.TestUtils.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderbookApplication.class)
@AutoConfigureMockMvc
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SecurityStorageEditTest {

    @Autowired
    SecurityStorageService securityStorageService;

    @BeforeEach
    void addSecurity() {
        securityStorageService.deleteBySymbol(buildSecurity().getSymbol());
        securityStorageService.add(buildSecurity());
    }

    @DisplayName("add security validations")
    @Test
    @Order(1)
    void addSecurityValidations() {
        // missing symbol
        assertThrows(BadRequestException.class,
                () -> securityStorageService.edit(null, buildSecurityMissingName()));

        // symbol not found
        assertThrows(BadRequestException.class,
                () -> securityStorageService.edit("MTNS", buildSecurityMissingName()));
    }

    @DisplayName("Should successfully edit security")
    @Test
    @Order(2)
    void editSecuritySuccess() {
        Security security = securityStorageService.edit(buildSecurity().getSymbol(), buildSecurityEdit());

        log.info("security ::::[{}]", security);
        Assertions.assertEquals(buildSecurityEdit().getSecurityType(), security.getSecurityType());
        Assertions.assertEquals(buildSecurityEdit().getName(), security.getName());
        Assertions.assertEquals(buildSecurityEdit().getStatus(), security.getStatus());
        Assertions.assertEquals(buildSecurityEdit().getSymbol(), security.getSymbol());
    }
}
