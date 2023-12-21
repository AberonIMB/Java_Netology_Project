package ru.netology.kriger.controller.DTO;

import lombok.Data;
import ru.netology.kriger.controller.DTO.CustomerDTO;

import java.util.List;

@Data
public class CustomersGetResponse {
    private final List<CustomerDTO> customers;

    public CustomersGetResponse(List<CustomerDTO> customers) {
        this.customers = customers;
    }
}