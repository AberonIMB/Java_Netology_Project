import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.kriger.Customer;
import ru.netology.kriger.Operation;
import ru.netology.kriger.services.StorageService;

public class StorageServiceTest {
    @Test
    public void getAndSetCustomerTest() {
        StorageService<Customer> customerStorageService = new StorageService<>();
        Customer customer = new Customer(2, "Jhon");
        customerStorageService.setElement(customer);

        Customer storageCustomer = customerStorageService.getElement(0);
        Assertions.assertEquals(customer, storageCustomer);
    }
    @Test
    public void getAndSetOperationTest() {
        StorageService<Operation> operationStorageService = new StorageService<>();
        Operation operation = new Operation(0, 123, "RUB", "coffe");
        operationStorageService.setElement(operation);

        Operation storageOperation = operationStorageService.getElement(0);
        Assertions.assertEquals(operation, storageOperation);
    }
}
