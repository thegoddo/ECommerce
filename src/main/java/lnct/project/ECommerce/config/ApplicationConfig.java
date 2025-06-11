package lnct.project.ECommerce.config;

import lnct.project.ECommerce.entities.User;
import lnct.project.ECommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class ApplicationConfig {

    @Autowired
    private UserRepo userRepo;

//    @Bean
//    pub:w
//    lic UserDetailsService userDetailsService(){
//        return username -> userRepo.findByEmail(username);
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            User user = userRepo.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),          // The username (email in your case)
                    user.getPassword(),       // The HASHED password from your User entity
                    Collections.emptyList()   // User's authorities/roles (can be empty for now or populate properly)
            );
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
