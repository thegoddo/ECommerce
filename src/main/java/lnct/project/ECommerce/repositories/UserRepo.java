package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByEmail(String e);
}
