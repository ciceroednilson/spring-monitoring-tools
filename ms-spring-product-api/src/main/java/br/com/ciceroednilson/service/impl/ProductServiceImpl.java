package br.com.ciceroednilson.service.impl;

import br.com.ciceroednilson.repository.ProductRepository;
import br.com.ciceroednilson.repository.entity.ProductEntity;
import br.com.ciceroednilson.service.ProductService;
import br.com.ciceroednilson.service.model.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final String[] ITEMS = {"games", "house", "car", "office", "clothes", "jewelry"};
    private final List<String> CATEGORIES = Arrays.asList(ITEMS);
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductModel find(final Long id) {
        log.info("Find a product by id {}", id);
        final Optional<ProductEntity> entity = this.repository.findById(id);
        return entity.map(productEntity -> entityToModel(entity.get())).orElse(null);
    }

    @Override
    public void delete(final Long id) {
        log.info("Delete a product by id {}", id);
        final Optional<ProductEntity> entity = this.repository.findById(id);
        entity.ifPresent(this.repository::delete);
    }

    @Override
    public List<ProductModel> findAll() {
        log.info("Find all products");
        final List<ProductModel> listModel = new ArrayList<>();
        final List<ProductEntity> list = this.repository.findAll();
        list.forEach(item -> {
            listModel.add(entityToModel(item));
        });
        return listModel;
    }

    @Override
    public void save(final ProductModel model) throws Exception {
        validateCategory(model.getCategory());
        log.info("Save the product {}", model.getName());
        final ProductEntity entity = modelToEntity(model);
        entity.setId(null);
        this.repository.save(entity);
    }

    @Override
    public void update(final ProductModel model) throws Exception {
        validateCategory(model.getCategory());
        log.info("Update the product {}", model.getName());
        final ProductEntity entity = modelToEntity(model);
        this.repository.save(entity);
    }

    @Override
    public List<ProductModel> sumByCategories() {
        log.info("Find sum by categories");
        final List<Object[]> list = this.repository.findTotalByCategory();
        final List<ProductModel> listModel = new ArrayList<>();
        list.forEach(item -> {
            listModel.add(
                    ProductModel
                            .builder()
                            .category(item[0].toString())
                            .total(Integer.parseInt(item[1].toString()))
                            .active(null)
                            .build());
        });

        return listModel;
    }

    private void validateCategory(final String category) throws Exception {
        if (!CATEGORIES.contains(category)) {
            log.error("Error to validate the category");
            throw new Exception(String.format("Category %s not found!", category));
        }
    }

    private ProductModel entityToModel(final ProductEntity entity) {
        return ProductModel
                .builder()
                .total(entity.getTotal())
                .name(entity.getName())
                .active(entity.isActive())
                .id(entity.getId())
                .amount(entity.getAmount())
                .category(entity.getCategory())
                .build();
    }

    private ProductEntity modelToEntity(final ProductModel model) {
        return ProductEntity
                .builder()
                .total(model.getTotal())
                .name(model.getName())
                .active(model.getActive())
                .id(model.getId())
                .amount(model.getAmount())
                .category(model.getCategory())
                .build();
    }
}
