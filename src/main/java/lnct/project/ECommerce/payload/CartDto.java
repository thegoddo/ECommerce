package lnct.project.ECommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDto {
    private int id;
    private UserDto user;

    private float totalAmount;

    private List<CartDetailDto> cartDetails;
}
