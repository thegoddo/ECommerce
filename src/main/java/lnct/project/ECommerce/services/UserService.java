package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.SignIn;
import lnct.project.ECommerce.payload.UserDto;

public interface UserService {
    UserDto CreateUser(UserDto userDto);

    SignIn SignIn(SignIn signIn);
}
