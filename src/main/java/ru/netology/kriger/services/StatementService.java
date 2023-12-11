package ru.netology.kriger.services;

import ru.netology.kriger.CustomerOperationOutOfBoundException;


public class StatementService {
    private final lOService lOService;

    public StatementService(lOService lOService) {
        this.lOService = lOService;
    }

    public void tryAddOperationToStatement(int customerId, int operationId) throws Exception {
        if (StorageService.getCustomers_operations_count()[customerId] < StorageService.getOPERATIONS_PER_CUSTOMERS()) {
            int operationsCountForCustomer = StorageService.getCustomers_operations_count()[customerId];
            StorageService.getStatement()[customerId][operationsCountForCustomer] = operationId;
            StorageService.getCustomers_operations_count()[customerId]++;
            lOService.printMessage("Операция успешно добавлена в выписку");
        } else {
            throw new CustomerOperationOutOfBoundException(customerId, operationId);
        }
    }
}