package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Cart;
import lnct.project.ECommerce.models.CartItem;
import lnct.project.ECommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    // Add other custom query methods if needed
}