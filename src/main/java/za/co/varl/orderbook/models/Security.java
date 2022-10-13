package za.co.varl.orderbook.models;

import lombok.*;
import za.co.varl.orderbook.utils.enums.EntityStatus;
import za.co.varl.orderbook.utils.enums.SecurityType;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Security {
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String name;
    private String symbol;
    private SecurityType securityType;
    private float balance;

    @Builder.Default
    private EntityStatus status = EntityStatus.ACTIVE;

    @Builder.Default
    private Date date = new Date();
}
