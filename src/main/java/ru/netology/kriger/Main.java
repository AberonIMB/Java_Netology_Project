package ru.netology.kriger;

import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public final static int MAX_OPERATIONS = 1_000;
    public final static int MAX_CUSTOMERS = 100;
    private final static Operation[] operations = new Operation[MAX_OPERATIONS];
    private final static Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final static int[] customers_operations_count = new int[MAX_CUSTOMERS];
    private final static int[][] statement = new int[MAX_CUSTOMERS][MAX_OPERATIONS / MAX_CUSTOMERS];


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        inputCustomer(scanner);
        inputOperations(scanner);


        System.out.println(Arrays.toString(operations));
        System.out.println(Arrays.toString(customers));
        System.out.println(Arrays.deepToString(statement));
        System.out.println(Arrays.toString(customers_operations_count));
    }

    private static void inputCustomer(Scanner scanner) {
        int customersCount = 0;

        while (true) {
            System.out.println("Введите имя клиента номер " + customersCount);
            String name = scanner.nextLine();

            customers[customersCount] = new Customer(customersCount, name);
            customersCount++;

            System.out.println("Хотите продолжить?: y/n");
            String answer = scanner.nextLine();

            if (customersCount == MAX_CUSTOMERS || answer.equals("n")) {
                break;
            }
        }
    }

    private static void inputOperations(Scanner scanner) throws Exception {
        int operationId = 0;

        while (true) {
            System.out.println("Введите сумму транзакции номер " + operationId);
            int sum = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Введите валюту транзакции номер " + operationId);
            String currency = scanner.nextLine();

            System.out.println("Введите получателя(merchant) из транзакции номер " + operationId);
            String merchant = scanner.nextLine();

            System.out.println("Введите id клиента этой операции");
            int customerId = scanner.nextInt();
            scanner.nextLine();

            if (!isCustomerFound(customerId)) {
                System.out.println("Клиент с таким id не найден.");
                System.out.println("Хотите ввести другой id?() Хотите " + "закончить добавление операций?(y/n)");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    break;
                }
                continue;
            }

            tryAddOperationToStatement(customerId, operationId);
            operations[operationId] = new Operation(operationId, sum, currency, merchant);
            operationId++;


            System.out.println("Хотите посмотреть операции клиента?: y/n");
            String answerId = scanner.nextLine();

            if (answerId.equals("y")) {
                System.out.println("Введите id клиента: ");
                int answer = scanner.nextInt();
                scanner.nextLine();

                if (isCustomerIdCorrect(answer)) {
                    System.out.println(Arrays.toString(getOperations(answer)));
                } else {
                    System.out.println("Такого клиента не существует");
                }
            }

            System.out.println("Хотите продолжить?: y/n");
            String answer = scanner.nextLine();

            if (operationId == MAX_OPERATIONS || answer.equals("n")) {
                break;
            }
        }
    }

    private static boolean isCustomerFound(int customerId) {

        if (!isCustomerIdCorrect(customerId)) {
            return false;
        }

        for (Customer customer : customers) {
            if (customer == null) {
                return false;
            }
            if (customer.getId() == customerId) {
                return true;
            }
        }

        return false;
    }

    private static boolean isCustomerIdCorrect(int customerId) {
        return customers.length > customerId && customers[customerId] != null;
    }

    public static Operation[] getOperations(int clientId) {
        int length = customers_operations_count[clientId];
        Operation[] result = new Operation[length];

        for (int i = 0; i < length; i++) {
            for (Operation operation : operations) {
                if (operation.getId() == statement[clientId][i]) {
                    result[i] = operation;
                    break;
                }
            }
        }

        return result;
    }

    private static void tryAddOperationToStatement(int customerId, int operationId) throws Exception {
        if (customers_operations_count[customerId] < MAX_OPERATIONS / MAX_CUSTOMERS) {
            int operationsCountForCustomer = customers_operations_count[customerId];
            statement[customerId][operationsCountForCustomer] = operationId;
            customers_operations_count[customerId]++;
            System.out.println("Операция успешно добавлена в выписку");
        } else {
            throw new CustomerOperationOutOfBoundException(customerId, operationId);
        }
    }
}