package ru.netology.kriger.controller.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
    private final int id;
    private final String name;

    public CustomerDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}