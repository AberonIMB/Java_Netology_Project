package ru.netology.kriger.services;

import ru.netology.kriger.Operation;

public class OperationService {
    private final StorageService<Operation> operations;

    public OperationService(StorageService<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(int id, int sum, String currency, String merchant) {
        operations.setElement(new Operation(id, sum, currency, merchant));
    }

    public Operation[] getOperationsById(int clientId) {
        int length = StorageService.getCustomers_operations_count()[clientId];
        Operation[] result = new Operation[length];

        for (int i = 0; i < length; i++) {
            for (Operation operation : operations.getStorage()) {
                if (operation.getId() == StorageService.getStatement()[clientId][i]) {
                    result[i] = operation;
                    break;
                }
            }
        }

        return result;
    }
}