package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Cart;
import lnct.project.ECommerce.models.CartDetails;
import lnct.project.ECommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailsRepo extends JpaRepository<CartDetails, Integer> {
    public void deleteByProductsAndCart(Product product, Cart cart);
    public CartDetails findByProductAndCart(Product product, Cart cart);
}
