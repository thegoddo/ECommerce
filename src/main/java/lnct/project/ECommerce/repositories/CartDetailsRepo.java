package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.entities.Cart;
import lnct.project.ECommerce.entities.CartDetalis;
import lnct.project.ECommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailsRepo extends JpaRepository<CartDetalis,Integer> {
    public void deleteByProductsAndCart(Product product, Cart cart);
    public CartDetalis findByProductsAndCart(Product product, Cart cart);
}
