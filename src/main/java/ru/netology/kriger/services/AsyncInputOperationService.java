package ru.netology.kriger.services;

import org.springframework.stereotype.Component;
import ru.netology.kriger.model.Operation;
import ru.netology.kriger.configuration.OperationProperties;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class AsyncInputOperationService {
    private Queue<Operation> queue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties operationProperties;

    public AsyncInputOperationService(StatementService statementService, OperationProperties operationProperties) {
        this.statementService = statementService;
        this.operationProperties = operationProperties;
    }

    @PostConstruct
    public void init(){
        this.startAsyncOperationProcessing();
    }

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("No operations");
                    Thread.sleep(operationProperties.sleepMilliSeconds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                statementService.addOperation(operation);
            }
        }
    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }
}

//
//    public AsyncInputOperationService(OperationService operationService) {
//        this.operationService = operationService;
//    }
//
//    public boolean offerOperation(Operation operation) {
//        return queue.offer(operation);
//    }
//
//    private void processQueue() {
//        while (true) {
//            Operation operation = queue.poll();
//            if (operation == null) {
//                try {
////                    System.out.println("Waiting for next operation in queue");
//                    Thread.sleep(1_000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                System.out.println("Processing operation:" + operation);
//                operationService.addOperation(operation);
//            }
//        }
//    }
//
//    public void startAsyncOperationProcessing() {
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                processQueue();
//            }
//        };
//        t.start();
//    }
//}