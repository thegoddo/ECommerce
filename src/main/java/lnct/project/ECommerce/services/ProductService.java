package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto CreateProduct(ProductDto productDto);

    ProductDto ReadProduct(Integer ProductId);

    List<ProductDto> ReadAllProduct();

    void DeleteProduct(Integer productId);

    ProductDto UpdateProduct(ProductDto productDto, Integer ProductId);
}
