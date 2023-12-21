package ru.netology.kriger.model;

import lombok.Data;
import ru.netology.kriger.ConsolePrintable;


@Data
public class Operation implements ConsolePrintable {
    private int id;
    private int sum;
    private String currency;
    private String merchant;
    private int customerId;

    public Operation() {

    }

    public Operation(int id, int sum, String currency, String merchant, int customerId) {
        this.id = id;
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
        this.customerId = customerId;
    }

    public void printToConsole() {
        System.out.println("Operation Information:");
        System.out.println("Id: " + id);
        System.out.println("sum: " + sum);
        System.out.println("currency: " + currency);
        System.out.println("merchant: " + merchant);
        System.out.println("customerId: " + customerId);
    }
}