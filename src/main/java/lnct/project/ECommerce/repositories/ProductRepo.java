package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
