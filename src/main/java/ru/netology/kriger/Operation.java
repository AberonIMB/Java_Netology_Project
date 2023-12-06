package ru.netology.kriger;

import lombok.Data;


@Data
public class Operation implements ConsolePrintable {
    private int id;
    private int sum;
    private String currency;
    private String merchant;

    public Operation() {

    }

    public Operation(int id, int sum, String currency, String merchant) {
        this.id = id;
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
    }

    public void printToConsole() {
        System.out.println("Operation Information:");
        System.out.println("Id: " + id);
        System.out.println("sum: " + sum);
        System.out.println("currency: " + currency);
        System.out.println("merchant: " + merchant);
    }
}