package br.com.ciceroednilson.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    private Long id;
    private String name;
    private BigDecimal amount;
    private int total;
    private String category;
    private boolean active;
}
