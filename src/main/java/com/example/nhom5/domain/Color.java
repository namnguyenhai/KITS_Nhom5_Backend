package com.example.nhom5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "color")
public class Color implements Serializable {
    @Id
    private String colorName;

    @OneToMany(mappedBy = "color")
    private List<Stock> stocks;
}