package ru.netology.kriger.services;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.netology.kriger.model.Operation;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Data
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    @PostConstruct
    public void initStorage() {
        addOperation(new Operation(0, 1000, "USD", "transport", 0));
        addOperation(new Operation(1, 2000, "RUB", "cafe", 0));
    }

    public void addOperation(Operation operation) {
        int customerId = operation.getCustomerId();
        if (storage.containsKey(customerId)) {
            storage.get(customerId).add(operation);
        } else {
            List<Operation> newCustomerOperations = new ArrayList<>();
            newCustomerOperations.add(operation);
            storage.put(customerId, newCustomerOperations);
        }
    }

    public List<Operation> getOperationsById(int customerId) {
        return storage.getOrDefault(customerId, new ArrayList<>());
    }

    public void deleteOperation(int id) {
        for (Map.Entry<Integer, List<Operation>> entry : storage.entrySet()) {
            int customerId = entry.getKey();
            List<Operation> operations = entry.getValue();            //Не самый лучший способ искать операцию, просто
            Optional<Operation> operationRemove = operations.stream() //у меня слишком мало времени, что бы переделать
                    .filter(operation -> operation.getId() == id)
                    .findFirst();

            operationRemove.ifPresent(operations::remove);
        }
    }
}