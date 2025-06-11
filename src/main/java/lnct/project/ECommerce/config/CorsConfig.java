package lnct.project.ECommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override // Override the method
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000") // Explicitly list your frontend development origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // List common methods
                .allowedHeaders("*") // Allow all headers for convenience in dev
                .allowCredentials(true) // If your frontend sends cookies/auth headers
                .maxAge(3600); // How long the pre-flight response can be cached (in seconds)
    }
    
}
