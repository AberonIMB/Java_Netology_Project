import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.kriger.Customer;
import ru.netology.kriger.Operation;
import ru.netology.kriger.services.CustomerService;
import ru.netology.kriger.services.OperationService;
import ru.netology.kriger.services.StorageService;

import java.util.function.ToDoubleBiFunction;

public class OperationServiceTest {
    private StorageService<Operation> operations;
    private OperationService operationService;

    @BeforeEach
    public void setUp() {
        operations = new StorageService<>();
        operationService = new OperationService(operations);
    }
    @Test
    public void addOperationTest() {
        operationService.addOperation(1, 12, "rub", "coffee");

        Assertions.assertEquals(new Operation(1, 12, "rub", "coffee"),
                operations.getElement(0));
    }

    @Test
    public void getOperationsById() {
    }
}
