package lnct.project.ECommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeStoreApiConfig {

    @Value("${fakestore.api.baseUrl}")
    private String baseUrl;

    @Value("${fakestore.api.products.endpoint}")
    private String productsEndpoint;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getProductsEndpoint() {
        return productsEndpoint;
    }

    public String getProductsApiUrl() {
        return baseUrl + productsEndpoint;
    }
}
