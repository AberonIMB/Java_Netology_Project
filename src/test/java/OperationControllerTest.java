import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.kriger.OperationHistoryApiApplication;
import ru.netology.kriger.controller.DTO.OperationDTO;
import ru.netology.kriger.controller.DTO.OperationsGetResponse;
import ru.netology.kriger.controller.OperationController;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = OperationHistoryApiApplication.class)
public class OperationControllerTest {
    @Autowired
    private OperationController operationController;
    @Test
    public void getOperationsTest() {
        OperationsGetResponse operations = operationController.getOperationsByUserId(0);

        OperationDTO operation1 = operations.getOperations().get(0);
        OperationDTO operation2 = operations.getOperations().get(1);

        assertEquals(0, operation1.getId());
        assertEquals(1, operation2.getId());
        assertEquals("transport", operation1.getMerchant());
        assertEquals("cafe", operation2.getMerchant());
    }

    @Test
    public void deleteOperationTest() {
        operationController.deleteOperation(0);

        OperationsGetResponse operations = operationController.getOperationsByUserId(0);
        OperationDTO operation = operations.getOperations().get(0);

        assertEquals(1, operation.getId());
    }
}
