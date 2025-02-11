package br.com.ciceroednilson.controller.request;

import br.com.ciceroednilson.service.model.ProductModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    private Long id;
    private String name;
    private BigDecimal amount;
    private int total;
    private String category;
    private boolean active;

    public ProductModel toModel() {
        return ProductModel
                .builder()
                .category(category)
                .amount(amount)
                .total(total)
                .id(id)
                .active(active)
                .name(name)
                .build();
    }
}
