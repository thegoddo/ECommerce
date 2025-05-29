package lnct.project.ECommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "users") // Renamed table to avoid potential conflicts with reserved keywords
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String address; // Or @ManyToOne to Address

    private String phoneNumber;


    @ElementCollection(fetch = FetchType.EAGER) // For simple roles
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles; // e.g., "ROLE_CUSTOMER", "ROLE_ADMIN"

    private LocalDateTime registrationDate = LocalDateTime.now();
}