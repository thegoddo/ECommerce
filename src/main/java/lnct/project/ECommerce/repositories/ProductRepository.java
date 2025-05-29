package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);
//    List<Product> findByPriceRange(@Param("minPrice")BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
}
