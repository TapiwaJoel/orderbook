package za.co.varl.orderbook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import za.co.varl.orderbook.utils.enums.EntityStatus;
import za.co.varl.orderbook.utils.enums.SecurityType;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Symbol is mandatory")
    private String symbol;

    @NotBlank(message = "SecurityType is mandatory")
    private SecurityType securityType;

    @NotBlank(message = "Balance is mandatory")
    private float balance;

    private EntityStatus entityStatus = EntityStatus.ACTIVE;
    private Date date;

    @PrePersist
    void preInsert() {
        if (this.date == null)
            this.date = new Date();
    }
}
