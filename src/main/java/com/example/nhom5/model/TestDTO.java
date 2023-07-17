package com.example.nhom5.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDTO {
    private int id;
    private String url;

    public TestDTO(int id, String url) {
        this.id = id;
        this.url = url;
    }
}
