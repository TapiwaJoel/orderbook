package za.co.varl.orderbook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import za.co.varl.orderbook.utils.enums.Currency;
import za.co.varl.orderbook.utils.enums.OrderSide;
import za.co.varl.orderbook.utils.enums.OrderStatus;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "OrderSide is mandatory")
    private OrderSide orderSide;

    @NotBlank(message = "Quantity is mandatory")
    private float quantity;

    @NotBlank(message = "Price is mandatory")
    private double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @NotBlank(message = "Currency is mandatory")
    private Currency currency;

    @ManyToOne
    @NotBlank(message = "Security is mandatory")
    private Security security;
    private Date date;

    @PrePersist
    void preInsert() {
        if (this.date == null)
            this.date = new Date();
    }
}
