package br.com.ciceroednilson.service.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductModel {
    private Long id;
    private String name;
    private BigDecimal amount;
    private int total;
    private String category;
    private Boolean active;
}
