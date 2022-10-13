package za.co.varl.orderbook;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.varl.orderbook.storage.DataStorage;

import java.util.HashMap;

@SpringBootApplication
public class OrderbookApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderbookApplication.class, args);
    }

//    @PostConstruct
//    void initialise() {
//        DataStorage.securities = new HashMap<>();
//    }
}
