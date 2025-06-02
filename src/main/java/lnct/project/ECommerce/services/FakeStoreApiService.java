package lnct.project.ECommerce.services;

import lnct.project.ECommerce.dtos.FakeStoreProductDto;

import java.util.List;
import java.util.Optional;

public interface FakeStoreApiService {
    List<FakeStoreProductDto> getAllProducts();
    Optional<FakeStoreProductDto> getProductById(Long id);
}
