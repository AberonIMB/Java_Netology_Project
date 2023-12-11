package ru.netology.kriger.services;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class StorageService<T> {
    @Getter
    private static final int MAX_OPERATIONS = 4;
    @Getter
    private static final int MAX_CUSTOMERS = 2;
    @Getter
    private static final int OPERATIONS_PER_CUSTOMERS = MAX_OPERATIONS / MAX_CUSTOMERS;
    @Getter
    private static final int[][] statement = new int[MAX_CUSTOMERS][OPERATIONS_PER_CUSTOMERS];
    @Getter
    private static final int[] customers_operations_count = new int[MAX_CUSTOMERS];
    private final List<T> storage = new ArrayList<>();

    public T getElement(int position) {
        return storage.get(position);
    }

    public void setElement(T element) {
        storage.add(element);
    }
}