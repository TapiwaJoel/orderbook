package za.co.varl.orderbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
