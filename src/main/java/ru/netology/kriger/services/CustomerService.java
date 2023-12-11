package ru.netology.kriger.services;

import ru.netology.kriger.Customer;

import java.util.List;

public class CustomerService {
//    private final List<Customer> customers;
    private final StorageService<Customer> customers;
    public CustomerService(StorageService<Customer> customers) {
        this.customers = customers;
    }

    public void AddCustomer(int customerId, String name) {
        customers.setElement(new Customer(customerId, name));
    }

    public boolean isCustomerFound(int customerId) {

        if (!isCustomerIdCorrect(customerId)) {
            return false;
        }

        for (Customer customer : customers.getStorage()) {
            if (customer == null) {
                return false;
            }
            if (customer.getId() == customerId) {
                return true;
            }
        }

        return false;
    }

    public boolean isCustomerIdCorrect(int customerId) {
        return customers.getStorage().size() > customerId && customers.getElement(customerId) != null;
    }
}