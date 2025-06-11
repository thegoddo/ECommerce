package lnct.project.ECommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // You'll adjust this
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // You'll remove the filter related to this

// import static lnct.project.ECommerce.entities.TotalRoles.ADMIN; // This is fine, but might not be used here if not checking roles

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //    @Autowired
//    private JwtAuthenticationFilter jwtAuthFilter; // <<< REMOVE THIS LINE (commented out already, but fully delete)
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Keep this if your frontend is not sending CSRF tokens (typical for SPAs)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // <<< ADJUST THIS: This permits ALL requests, making authentication pointless.
                        // You likely want specific paths permitted (like /signin, /signup)
                        // and others authenticated.
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(session -> session
                        // <<< ADJUST THIS: If you're removing JWT, you'll want stateful sessions.
                        // Remove or comment out SessionCreationPolicy.STATELESS
                        // Default is IF_REQUIRED, which allows sessions.
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Use IF_REQUIRED or REMOVE this line entirely for default
                )
                .authenticationProvider(authenticationProvider) // Keep this, as your custom provider is still needed for authentication
                // <<< REMOVE THIS LINE ENTIRELY: This was for JWT filter
                // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                // Add form login configuration for traditional session management
                .formLogin(form -> form
                        .loginPage("/login") // Frontend route for login page (optional, but good practice)
                        .loginProcessingUrl("/perform_login") // URL where frontend POSTs credentials
                        .defaultSuccessUrl("/", true) // Redirect after successful login
                        .failureUrl("/login?error") // Redirect on login failure
                        .permitAll() // Allow access to login page/endpoint
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout") // URL to trigger logout
                        .logoutSuccessUrl("/login?logout") // Redirect after logout
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll()
                );


        return http.build();
    }
}