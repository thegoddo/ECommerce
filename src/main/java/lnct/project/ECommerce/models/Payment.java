package lnct.project.ECommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    private String paymentMethod; // Consider using an enum

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    private String transactionId; // From the payment gateway

    private String status; // Consider using an enum

    private LocalDateTime paymentDate = LocalDateTime.now();
}