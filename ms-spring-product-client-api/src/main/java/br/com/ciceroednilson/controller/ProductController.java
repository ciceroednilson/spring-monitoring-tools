package br.com.ciceroednilson.controller;

import br.com.ciceroednilson.repository.entity.ProductEntity;
import br.com.ciceroednilson.service.ProductService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private Counter counter;
    private MeterRegistry meterRegistry;

    @Autowired
    public ProductController(final ProductService productService, final MeterRegistry meterRegistry) {
        this.productService = productService;
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long id) {
        metricsIncrement("findById");
        ProductEntity product = productService.findProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {
        metricsIncrement("findAll");
        List<ProductEntity> products = productService.findAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        products.forEach( item -> {
            metricsGauge(item.getName(), item.getTotal());
        });
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/sum-by-categories")
    public ResponseEntity<List<ProductEntity>> sumProductsByCategories() {
        metricsIncrement("sumProductsByCategories");
        List<ProductEntity> products = productService.sumProductsByCategories();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductEntity productEntity) {
        metricsIncrement("save");
        String response = productService.saveProduct(productEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductEntity productEntity) {
        metricsIncrement("update");
        productEntity.setId(id);
        String response = productService.updateProduct(productEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        metricsIncrement("delete");
        String response = productService.deleteProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void metricsIncrement(final String method) {
        this.counter = Counter
                .builder("news_fetch_request_product_total")
                .tag("version", "1.0")
                .tag("method", method)
                .description("Method executed")
                .register(meterRegistry);
        counter.increment();
    }

    private void metricsGauge(final String name, final Integer total) {
        Gauge.builder("news_total_products", () -> total)
                .tag("version", "1.0")
                .tag("product", name)
                .tag("product_total", String.valueOf(total))
                .description("Total Records")
                .register(meterRegistry);
    }
}
