package br.com.ciceroednilson.repository;

import br.com.ciceroednilson.repository.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {

    final RestTemplate restTemplate;

    @Value("${external_api.save}")
    private String urlSave;

    @Value("${external_api.update_by_id}")
    private String urlUpdateById;

    @Value("${external_api.delete_by_id}")
    private String urlDeleteById;

    @Value("${external_api.find_by_id}")
    private String urlFindById;

    @Value("${external_api.find_all}")
    private String urlFindAll;

    @Value("${external_api.sum_by_categories}")
    private String urlSumByCategories;

    @Autowired
    public ProductRepository(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductEntity findById(final Long id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<ProductEntity> response = restTemplate.exchange(urlFindById.replace("{id}", id.toString()), HttpMethod.GET, entity, ProductEntity.class);
        return response.getBody();
    }

    public List<ProductEntity> findAll() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<ProductEntity[]> response = restTemplate.exchange(urlFindAll, HttpMethod.GET, entity, ProductEntity[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<ProductEntity> sumByCategories() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<ProductEntity[]> response = restTemplate.exchange(urlSumByCategories, HttpMethod.GET, entity, ProductEntity[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public String save(final ProductEntity productEntity) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<ProductEntity> entity = new HttpEntity<>(productEntity, headers);
        final ResponseEntity<String> response = restTemplate.exchange(urlSave, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    public String update(final ProductEntity productEntity) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<ProductEntity> entity = new HttpEntity<>(productEntity, headers);
        final ResponseEntity<String> response = restTemplate.exchange(urlUpdateById.replace("{id}", productEntity.getId().toString()), HttpMethod.PUT, entity, String.class);
        return response.getBody();
    }

    public String delete(final Long id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<ProductEntity> entity = new HttpEntity<>(headers);
        final ResponseEntity<String> response = restTemplate.exchange(urlDeleteById.replace("{id}", id.toString()), HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }
}
