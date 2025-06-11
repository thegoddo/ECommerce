package lnct.project.ECommerce.services.impl;

import lnct.project.ECommerce.entities.Product;
import lnct.project.ECommerce.payload.ProductDto;
import lnct.project.ECommerce.repositories.ProductRepo;
import lnct.project.ECommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater; // Assuming you're using Deflater for compression
import java.util.zip.Inflater;

import static lnct.project.ECommerce.util.ImageUtils.compressBytes; // Assuming these are in a util class
import static lnct.project.ECommerce.util.ImageUtils.decompressBytes; // Assuming these are in a util class

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto CreateProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        // Compress the image bytes before saving
        if (productDto.getImg() != null) { // Add null check before compressing
            product.setImg(compressBytes(productDto.getImg()));
        } else {
            product.setImg(null); // Explicitly set to null if no image was provided
        }
        Product savedProduct = this.productRepo.save(product);
        return this.modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto ReadProduct(Integer productid) {
        Product product = this.productRepo.findById(productid)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productid)); // Or a custom exception
        ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
        // Decompress the image bytes before returning
        if (product.getImg() != null) { // Crucial null check here!
            productDto.setImg(decompressBytes(product.getImg()));
        } else {
            productDto.setImg(null); // Ensure DTO's img is null if DB img is null
        }
        return productDto;
    }

    @Override
    public List<ProductDto> ReadAllProduct() {
        List<Product> products = this.productRepo.findAll();

        return products.stream().map(product -> {
            ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
            // Decompress the image bytes for each product
            if (product.getImg() != null) { // Crucial null check before decompression
                productDto.setImg(decompressBytes(product.getImg()));
            } else {
                productDto.setImg(null); // Set DTO image to null if DB image is null
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void DeleteProduct(Integer productId) {
        Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        this.productRepo.delete(product);
    }

    @Override
    public ProductDto UpdateProduct(ProductDto productDto, Integer productId) {
        Product existingProduct = this.productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setWeight(productDto.getWeight());
        if (productDto.getImg() != null) { // Add null check before compressing
            existingProduct.setImg(compressBytes(productDto.getImg()));
        } else {
            existingProduct.setImg(null); // Allow updating image to null if client sends null
        }

        Product updatedProduct = this.productRepo.save(existingProduct);
        return this.modelMapper.map(updatedProduct, ProductDto.class);
    }

    // Assuming your ImageUtils class or these methods are part of this class
    // You mentioned the error is in decompressBytes at line 123, so I'm assuming it's here
    public static byte[] decompressBytes(byte[] data) {
        // --- THIS IS THE CRUCIAL NULL CHECK ---
        if (data == null) {
            System.out.println("Decompression: Input data is null. Returning null.");
            return null;
        }
        // --- END OF CRUCIAL NULL CHECK ---

        Inflater inflater = new Inflater();
        inflater.setInput(data); // This is line 123 where the error occurs
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[4 * 1024]; // 4KB buffer
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            System.err.println("Error during decompression: " + e.getMessage());
            // Log the exception, return null or throw a custom exception
            return null; // Return null if decompression fails
        }
        return outputStream.toByteArray();
    }

    // Assuming you have a compressBytes method too
    public static byte[] compressBytes(byte[] data) {
        // --- Add null check here too, if it's not already done before calling this method ---
        if (data == null) {
            System.out.println("Compression: Input data is null. Returning null.");
            return null;
        }
        // --- END OF NULL CHECK ---

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[4 * 1024]; // 4KB buffer
        try {
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            System.err.println("Error during compression: " + e.getMessage());
            // Log the exception, return null or throw a custom exception
            return null;
        }
        return outputStream.toByteArray();
    }
}