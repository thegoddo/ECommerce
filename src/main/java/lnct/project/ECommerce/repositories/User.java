package lnct.project.ECommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User extends JpaRepository<User, Integer> {
    public User findByEmail(String e);
}
