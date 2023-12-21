package ru.netology.kriger.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.kriger.model.Operation;
import ru.netology.kriger.controller.DTO.OperationDTO;
import ru.netology.kriger.controller.DTO.OperationsGetResponse;
import ru.netology.kriger.services.AsyncInputOperationService;
import ru.netology.kriger.services.CustomerService;
import ru.netology.kriger.services.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/operations")
@AllArgsConstructor
public class OperationController {
    private final StatementService statementService;
    private final AsyncInputOperationService asyncInputOperationService;
    private final CustomerService customerService;
    private static int operationId = 2;

    @GetMapping("{Id}")
    public OperationsGetResponse getOperationsByUserId(@PathVariable("Id") int customerId){
        List<Operation> operations = statementService.getOperationsById(customerId);
        List<OperationDTO> operationsDTOS = operations.stream()
                .map(operation -> new OperationDTO(operation.getId(),
                        operation.getSum(),
                        operation.getCurrency(),
                        operation.getMerchant()))
                .collect(Collectors.toList());
        return new OperationsGetResponse(operationsDTOS);
    }

    @PostMapping
    public OperationDTO addOperation(int sum, String currency, String merchant, int customerId) {
        if (customerService.getCustomer(customerId) != null) {
            int id = operationId;
            operationId++;
            asyncInputOperationService.offerOperation(new Operation(id, sum, currency, merchant, customerId));
            return new OperationDTO(id, sum, currency, merchant);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable int id) {
        statementService.deleteOperation(id);
    }
}