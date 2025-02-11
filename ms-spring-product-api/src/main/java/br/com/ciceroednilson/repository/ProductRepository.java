package br.com.ciceroednilson.repository;

import br.com.ciceroednilson.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p.category, SUM(p.total) as total FROM ProductEntity p WHERE p.active = true GROUP BY p.category")
    List<Object[]> findTotalByCategory();
}
