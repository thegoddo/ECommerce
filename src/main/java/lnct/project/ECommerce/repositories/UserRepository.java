package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);


    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
