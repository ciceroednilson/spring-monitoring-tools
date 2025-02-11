package br.com.ciceroednilson.controller;

import br.com.ciceroednilson.controller.request.ProductRequest;
import br.com.ciceroednilson.controller.response.ProductResponse;
import br.com.ciceroednilson.service.ProductService;
import br.com.ciceroednilson.service.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductRequest request) throws Exception {
        this.productService.save(request.toModel());
        return ResponseEntity.ok("Save Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductRequest request) throws Exception {
        request.setId(id);
        this.productService.update(request.toModel());
        return ResponseEntity.ok("Update Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.ok("Delete Successfully");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        final List<ProductModel> listModel = this.productService.findAll();
        return ResponseEntity.ok(ProductResponse.toListProductResponse(listModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> find(@PathVariable Long id) {
        final ProductModel model = this.productService.find(id);
        return ResponseEntity.ok(ProductResponse.toProductResponse(model));
    }

    @GetMapping("/sum")
    public ResponseEntity<List<ProductResponse>> findSumCategories() {
        final List<ProductModel> listModel = this.productService.sumByCategories();
        return ResponseEntity.ok(ProductResponse.toListProductResponse(listModel));
    }
}
