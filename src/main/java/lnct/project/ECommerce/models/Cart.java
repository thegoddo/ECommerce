package lnct.project.ECommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private float TotalAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartDetails> cartDetails;


    public void setCartDetails(List<CartDetails> pro) {

    }
}
