package ru.netology.kriger.controller.DTO;

import lombok.Data;

@Data
public class OperationDTO {
    private final int id;
    private final int sum;
    private final String currency;
    private final String merchant;


    public OperationDTO(int id, int sum, String currency, String merchant) {
        this.id = id;
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
    }
}