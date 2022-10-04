package za.co.varl.orderbook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import za.co.varl.orderbook.utils.enums.EntityStatus;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "FullName is mandatory")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "PhoneNumber is mandatory")
    private String phoneNumber;

    private EntityStatus entityStatus = EntityStatus.ACTIVE;

    private Date date;

    @PrePersist
    void preInsert() {
        if (this.date == null)
            this.date = new Date();
    }
}
