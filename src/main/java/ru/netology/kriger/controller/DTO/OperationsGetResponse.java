package ru.netology.kriger.controller.DTO;

import lombok.Data;
import ru.netology.kriger.controller.DTO.OperationDTO;

import java.util.List;

@Data
public class OperationsGetResponse {
    private final List<OperationDTO> operations;

    public OperationsGetResponse(List<OperationDTO> operation) {
        this.operations = operation;
    }
}
