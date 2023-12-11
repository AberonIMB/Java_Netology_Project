import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.kriger.Customer;
import ru.netology.kriger.services.CustomerService;
import ru.netology.kriger.services.StorageService;

public class CustomerServiceTest {
    private StorageService<Customer> customers;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customers = new StorageService<>();
        customerService = new CustomerService(customers);
    }
    @Test
    public void addCustomerTest() {
        CustomerService customerService = new CustomerService(customers);
        customerService.AddCustomer(1, "dan");
        Assertions.assertEquals(new Customer(1, "dan"), customers.getElement(0));
    }

    @Test
    public void isCustomerFoundTest() {
        Integer intNull = null;
        customerService.AddCustomer(0, "David");
        customerService.AddCustomer(4, "Bob");

        Assertions.assertTrue(customerService.isCustomerFound(0));
        Assertions.assertFalse(customerService.isCustomerFound(4));
        Assertions.assertFalse(customerService.isCustomerFound(2));

    }

    @Test
    public void isCustomerIdCorrectTest() {
        customers.setElement(new Customer(0, "Jake"));

        Assertions.assertTrue(customerService.isCustomerIdCorrect(0));
        Assertions.assertFalse(customerService.isCustomerIdCorrect(1));
    }
}
