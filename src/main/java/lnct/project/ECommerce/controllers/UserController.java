package lnct.project.ECommerce.controllers;

import lnct.project.ECommerce.entities.User;
import lnct.project.ECommerce.payload.SingIn;
import lnct.project.ECommerce.payload.UserDto;
import lnct.project.ECommerce.repositories.UserRepo;
import lnct.project.ECommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/singup")
    public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto) {

        UserDto userDto1 = this.userService.CreateUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatusCode.valueOf(200));
    }


    @PostMapping("/singin")
    public ResponseEntity<?> authenticateUser(@RequestBody SingIn authenticationRequest) { // Use AuthenticationRequest here
        try {
            // 1. Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            // Set the authenticated user in the SecurityContext (important for session management)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 2. Retrieve the User entity from the database using the authenticated email/username
            // The authentication.getName() returns the username (which is email in your case)
            User user = userRepository.findByEmail(authentication.getName());
//                    .orElseThrow(() -> new RuntimeException("User not found after authentication. This should not happen."));

            // 3. Map the User entity to UserDto
            UserDto userDto = modelMapper.map(user, UserDto.class);

            // Ensure all fields you need in UserDto are correctly set from the User entity
            // ModelMapper usually handles matching names, but for specific cases:
            userDto.setUserid(user.getUserid()); // Assuming User entity has getId()
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setContact(user.getContact()); // Make sure User entity has getContact()
            userDto.setRole(user.getRole().toString()); // Make sure User entity has getRole()
            // If you have a 'date' field in UserDto and it's for signup date, map it
            // userDto.setDate(user.getSignupDate()); // Example

            // If you have other fields like avatarUrl, linkedin, github in UserDto and User entity, map them here:
            // userDto.setAvatarUrl(user.getAvatarUrl());
            // userDto.setLinkedin(user.getLinkedin());
            // userDto.setGithub(user.getGithub());

            // 4. Return the UserDto in the response
            return ResponseEntity.ok(userDto);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password.");
        } catch (Exception e) {
            // It's good practice to log the full exception stack trace for internal debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error: " + e.getMessage());
        }
    }
}
