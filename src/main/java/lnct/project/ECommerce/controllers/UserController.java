package lnct.project.ECommerce.controllers;

import lnct.project.ECommerce.payload.SignIn;
import lnct.project.ECommerce.payload.UserDto;
import lnct.project.ECommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/singup")
    public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto){

        UserDto userDto1 = this.userService.CreateUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatusCode.valueOf(200));
    }


    @PostMapping("/singin")
    public ResponseEntity<SignIn> CreateUser(@RequestBody SignIn singIn){

        SignIn singIn1 = this.userService.SignIn(singIn);
        return new ResponseEntity<>(singIn1, HttpStatusCode.valueOf(200));
    }
}

