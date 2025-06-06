package lnct.project.ECommerce.services.impl;

import lnct.project.ECommerce.dtos.FakeStoreProductDto;
import lnct.project.ECommerce.services.FakeStoreApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreApiServiceImpl implements FakeStoreApiService {

    private final RestTemplate restTemplate;
    private final FakeStoreApiConfig fakeStoreApiConfig;

    @Autowired
    public FakeStoreApiServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreApiConfig fakeStoreApiConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.fakeStoreApiConfig = fakeStoreApiConfig;
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {
        String url = fakeStoreApiConfig.getProductsApiUrl();
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
        );
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        return List.of(); // Return an empty list on failure
    }

    @Override
    public Optional<FakeStoreProductDto> getProductById(Long id) {
        String url = fakeStoreApiConfig.getProductsApiUrl() + "/" + id;
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                FakeStoreProductDto.class
        );
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    // Implement other methods to interact with different endpoints of the fake store API
}