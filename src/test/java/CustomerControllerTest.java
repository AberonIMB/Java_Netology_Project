import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.kriger.OperationHistoryApiApplication;
import ru.netology.kriger.controller.CustomerController;
import ru.netology.kriger.controller.DTO.CustomerDTO;
import ru.netology.kriger.controller.DTO.CustomersGetResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = OperationHistoryApiApplication.class)
public class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;
    @Test
    public void getClientsTest() {
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(0, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(1, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }
}