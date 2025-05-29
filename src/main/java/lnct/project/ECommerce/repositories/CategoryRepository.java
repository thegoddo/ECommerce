package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    List<Category> findByParentCategoryIsNull(); // Find top-level  categories
    List<Category> findByParentCategory( Category parentCategory);
}
