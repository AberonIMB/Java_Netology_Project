package ru.netology.kriger.services;

import java.util.Scanner;

public class lOService {
    Scanner scanner = new Scanner(System.in);
    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public Object[] readData(int operationId) {
        printMessage("Введите сумму транзакции номер " + operationId);
        int sum = readInt();

        printMessage("Введите валюту транзакции номер " + operationId);
        String currency = readString();

        printMessage("Введите получателя(merchant) из транзакции номер " + operationId);
        String merchant = readString();

        printMessage("Введите id клиента этой операции");
        int customerId = readInt();

        return new Object[] {sum, currency, merchant, customerId};
    }
}