package br.com.ciceroednilson.service;

import br.com.ciceroednilson.repository.ProductRepository;
import br.com.ciceroednilson.repository.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity findProductById(final Long id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductEntity> sumProductsByCategories() {
        return productRepository.sumByCategories();
    }

    public String saveProduct(final ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public String updateProduct(final ProductEntity productEntity) {
        return productRepository.update(productEntity);
    }

    public String deleteProduct(final Long id) {
        return productRepository.delete(id);
    }
}
