package br.com.ciceroednilson.controller.response;

import br.com.ciceroednilson.service.model.ProductModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Long id;
    private String name;
    private BigDecimal amount;
    private int total;
    private String category;
    private Boolean active;

    public static List<ProductResponse> toListProductResponse(final List<ProductModel> listModel) {
        final List<ProductResponse> listResponse = new ArrayList<>();
        listModel.forEach(model ->{
            listResponse.add(toProductResponse(model));
        });
        return listResponse;
    }

    public static ProductResponse toProductResponse(final ProductModel model) {
        if (Objects.nonNull(model)) {
            return ProductResponse
                    .builder()
                    .category(model.getCategory())
                    .amount(model.getAmount())
                    .total(model.getTotal())
                    .id(model.getId())
                    .active(model.getActive())
                    .name(model.getName())
                    .build();
        }
        return null;
    }
}
