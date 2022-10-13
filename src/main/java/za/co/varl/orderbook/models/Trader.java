package za.co.varl.orderbook.models;

import lombok.*;
import za.co.varl.orderbook.utils.enums.EntityStatus;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trader {

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String fullName;
    private String email;
    private String phoneNumber;

    @Builder.Default
    private EntityStatus status = EntityStatus.ACTIVE;

    @Builder.Default
    private Date date = new Date();
}
