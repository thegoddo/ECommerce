package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String e);
}
