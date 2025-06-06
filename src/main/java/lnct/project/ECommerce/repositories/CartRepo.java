package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Cart;
import lnct.project.ECommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    public Cart findByUser(User user);
}
