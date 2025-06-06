package lnct.project.ECommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CartDetailsId;

    @ManyToOne
    private Product products;
    private int Quantity;
    private int Amount;

    @ManyToOne
    private Cart cart;
}
