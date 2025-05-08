package lnct.project.ECommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductDto {
    private int ProductId;
    private  String productName;
    private String description;
    private Float Price;
    private Float weight;
    private byte[] img;
}
