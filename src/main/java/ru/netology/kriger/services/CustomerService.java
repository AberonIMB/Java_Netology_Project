package ru.netology.kriger.services;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.netology.kriger.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@Data
public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();

    @PostConstruct
    public void initStorage() {
        addCustomer(0, "Spring");
        addCustomer(1, "Boot");
    }

    public void addCustomer(int customerId, String name) {
        storage.add(new Customer(customerId, name));
    }

    public Customer getCustomer(int id) {
        return storage.get(id);
    }
}