package lnct.project.ECommerce.repositories;

import lnct.project.ECommerce.models.Order;
import lnct.project.ECommerce.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder(Order order);
    // Add other custom query methods if needed
}