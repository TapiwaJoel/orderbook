package za.co.varl.orderbook.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Portfolio {
    private Long id;
    private Trader trader;
    private List<Security> securities;
}
