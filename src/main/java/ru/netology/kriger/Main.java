package ru.netology.kriger;

import ru.netology.kriger.services.*;

import java.util.Arrays;


public class Main {
    private static final StorageService<Customer> customerStorageService = new StorageService<>();
    private static final StorageService<Operation> operationStorageService = new StorageService<>();
    private static final lOService lOService = new lOService();
    private static final CustomerService customerService = new CustomerService(customerStorageService);
    private static final OperationService operationService = new OperationService(operationStorageService);
    private static final StatementService statementService = new StatementService(lOService);

    public static void main(String[] args) throws Exception {
        inputCustomer();
        inputOperations();

        System.out.println(Arrays.toString(customerStorageService.getStorage().toArray()));
        System.out.println(Arrays.toString(operationStorageService.getStorage().toArray()));
        System.out.println(Arrays.deepToString(StorageService.getStatement()));
        System.out.println(Arrays.toString(StorageService.getCustomers_operations_count()));
    }

    private static void inputCustomer() {
        int customersCount = 0;

        while (true) {
            lOService.printMessage("Введите имя клиента номер " + customersCount);
            String name = lOService.readString();

            customerService.AddCustomer(customersCount, name);
            customersCount++;

            lOService.printMessage("Хотите продолжить?: y/n");
            String answer = lOService.readString();

            if (customersCount == StorageService.getMAX_CUSTOMERS() || answer.equals("n")) {
                break;
            }
        }
    }

    private static void inputOperations() throws Exception {
        int operationId = 0;

        while (true) {
            Object[] operationData = lOService.readData(operationId);
            int sum = (int)operationData[0];
            String currency = (String)operationData[1];
            String merchant = (String)operationData[2];
            int customerId = (int)operationData[3];

            if (!customerService.isCustomerFound(customerId)) {
                lOService.printMessage("Клиент с таким id не найден.");
                lOService.printMessage("Хотите ввести другой id?() Хотите закончить добавление операций?(y/n)");
                String answer = lOService.readString();
                if (answer.equals("y")) {
                    break;
                }
                continue;
            }

            statementService.tryAddOperationToStatement(customerId, operationId);

            operationService.addOperation(operationId, sum, currency, merchant);
            operationId++;

            tryCheckCustomerOperation();

            lOService.printMessage("Хотите продолжить?: y/n");
            String answer = lOService.readString();

            if (operationId == StorageService.getMAX_OPERATIONS() || answer.equals("n")) {
                break;
            }
        }
    }

    private static void tryCheckCustomerOperation() {
        lOService.printMessage("Хотите посмотреть операции клиента?: y/n");
        String answer = lOService.readString();

        if (answer.equals("y")) {
            lOService.printMessage("Введите id клиента: ");
            int id = lOService.readInt();

            if (customerService.isCustomerIdCorrect(id)) {
                lOService.printMessage(Arrays.toString(operationService.getOperationsById(id)));
            } else {
                lOService.printMessage("Такого клиента не существует");
            }
        }
    }
}