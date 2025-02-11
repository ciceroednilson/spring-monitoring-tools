package br.com.ciceroednilson.service;

import br.com.ciceroednilson.service.model.ProductModel;

import java.util.List;

public interface ProductService {

    ProductModel find(final Long id);
    void delete(final Long id);
    List<ProductModel> findAll();
    void save(final ProductModel model) throws Exception;
    void update(final ProductModel model) throws Exception;
    List<ProductModel> sumByCategories();
}
