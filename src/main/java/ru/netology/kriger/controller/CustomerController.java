package ru.netology.kriger.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.kriger.model.Customer;
import ru.netology.kriger.controller.DTO.CustomerDTO;
import ru.netology.kriger.controller.DTO.CustomersGetResponse;
import ru.netology.kriger.services.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private static int customerId = 2;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.getStorage();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(), customer.getName());
    }

    @PostMapping
    public CustomerDTO createCustomer(String name) {
        int id = customerId;
        customerService.addCustomer(id, name);
        customerId++;
        return new CustomerDTO(id, name);
    }
}