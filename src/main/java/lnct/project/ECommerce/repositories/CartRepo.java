package lnct.project.ECommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    public Cart findByUser(User user);
}
