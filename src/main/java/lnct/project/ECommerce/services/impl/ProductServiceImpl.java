package lnct.project.ECommerce.services.impl;

import lnct.project.ECommerce.dtos.FakeStoreProductDto;
import lnct.project.ECommerce.models.Product;
import lnct.project.ECommerce.repositories.ProductRepository;
import lnct.project.ECommerce.services.FakeStoreApiService;
import lnct.project.ECommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final FakeStoreApiService fakeStoreApiService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FakeStoreApiService fakeStoreApiService) {
        this.productRepository = productRepository;
        this.fakeStoreApiService = fakeStoreApiService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productDetails.getName());
                    existingProduct.setDescription(productDetails.getDescription());
                    existingProduct.setPrice(productDetails.getPrice());
                    existingProduct.setStockQuantity(productDetails.getStockQuantity());
                    existingProduct.setImageUrl(productDetails.getImageUrl());
                    existingProduct.setCategory(productDetails.getCategory());
                    existingProduct.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(existingProduct);
                }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public void syncProductsFromFakeStore() {
        List<FakeStoreProductDto> fakeStoreProducts = fakeStoreApiService.getAllProducts();
        if (fakeStoreProducts != null && !fakeStoreProducts.isEmpty()) {
            List<Product> productsToSave = fakeStoreProducts.stream()
                    .map(this::convertToProductEntity)
                    .collect(Collectors.toList());
            productRepository.saveAll(productsToSave);
            System.out.println("Successfully synced " + productsToSave.size() + " products from Fake Store API.");
        } else {
            System.out.println("No products to sync from Fake Store API or API call failed.");
        }
    }

    private Product convertToProductEntity(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setName(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice() != null ? BigDecimal.valueOf(Long.parseLong(dto.getPrice())) : BigDecimal.ZERO);
        product.setImageUrl(dto.getImage());
        product.setCategory(dto.getCategory());
        product.setStockQuantity(100);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}