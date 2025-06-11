package lnct.project.ECommerce.services.impl;

import lnct.project.ECommerce.config.JwtService;
import lnct.project.ECommerce.entities.Cart;
import lnct.project.ECommerce.entities.Role;
import lnct.project.ECommerce.entities.TotalRoles;
import lnct.project.ECommerce.entities.User;
import lnct.project.ECommerce.payload.SingIn;
import lnct.project.ECommerce.payload.UserDto;
import lnct.project.ECommerce.repositories.UserRepo;
import lnct.project.ECommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDto CreateUser(UserDto userDto) {
        User user= this.modelMapper.map(userDto, User.class);
        List<Role> list= new ArrayList<>();
                list.add(new Role(TotalRoles.ADMIN.name()));
        user.setRole(list);
        user.setPassword(passwordEncoder.encode(userDto.getRawPassword()));
        Cart cart= new Cart();
        cart.setUser(user);
        user.setCart(cart);

        this.userRepo.save(user);
        return this.modelMapper.map(user,UserDto.class);
    }
//
    @Override
    public SingIn SingIn(SingIn singIn) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(singIn.getEmail(),singIn.getPassword()));
        User user=this.userRepo.findByEmail(singIn.getEmail());
        var jwtToken=jwtService.generateToken(user);
        singIn.setJwt(jwtToken);
        return singIn;
    }
//
//    @Override
//    public SingIn SingIn(SingIn singIn) {
//        System.out.println("--- Sign-In Attempt Started ---");
//        System.out.println("1. Email from request: '" + singIn.getEmail() + "'");
//        // CAUTION: Only log raw password during development/debugging. Remove in production!
//        System.out.println("2. Raw password from request: '" + singIn.getPassword() + "'");
//
//        try {
//            // Load UserDetails explicitly to inspect the stored password
//            UserDetails userDetails = userDetailsService.loadUserByUsername(singIn.getEmail());
//            System.out.println("3. UserDetails found for email: '" + userDetails.getUsername() + "'");
//            System.out.println("4. Hashed password from UserDetails (from DB): '" + userDetails.getPassword() + "'");
//
//            // Compare passwords using the same encoder
//            boolean passwordMatches = passwordEncoder.matches(singIn.getPassword(), userDetails.getPassword());
//            System.out.println("5. PasswordEncoder.matches() result: " + passwordMatches);
//
//            // This is the line that will throw BadCredentialsException if passwordMatches is false
//            this.authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(singIn.getEmail(), singIn.getPassword())
//            );
//
//            System.out.println("6. Authentication successful for email: '" + singIn.getEmail() + "'");
//
//            // If authentication succeeds, load the user details again (or use the authenticated principal)
//            User user = this.userRepo.findByEmail(singIn.getEmail());
//            var jwtToken = jwtService.generateToken(user);
//            singIn.setJwt(jwtToken);
//            System.out.println("--- Sign-In Attempt Ended Successfully ---");
//            return singIn;
//
//        } catch (Exception e) {
//            System.err.println("--- Sign-In Attempt Failed ---");
//            System.err.println("Error for email: '" + singIn.getEmail() + "'");
//            System.err.println("Exception Type: " + e.getClass().getName());
//            System.err.println("Exception Message: " + e.getMessage());
//            // It's helpful to print the stack trace for deeper understanding, but can be verbose
//            // e.printStackTrace();
//            System.err.println("--- Sign-In Attempt Ended (Failed) ---");
//            throw e; // Re-throw the exception so the controller can handle it
//        }
//    }
}
