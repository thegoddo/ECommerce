package lnct.project.ECommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {

    private int CartDetailsId;
    private ProductDto products;
    private int quantity;
    private int amount;
}
