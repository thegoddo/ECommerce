package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Address;
import lnct.project.ECommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
    // Add other custom query methods if needed
}