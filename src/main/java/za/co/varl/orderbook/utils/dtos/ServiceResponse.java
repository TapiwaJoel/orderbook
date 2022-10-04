package za.co.varl.orderbook.utils.dtos;

import lombok.Data;


@Data
public class ServiceResponse<T> {
    private String message;
    private T data;
}

