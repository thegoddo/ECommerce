package lnct.project.ECommerce.services;


import lnct.project.ECommerce.payload.SingIn;
import lnct.project.ECommerce.payload.UserDto;

public interface UserService {


    UserDto CreateUser(UserDto userDto);

    SingIn SingIn(SingIn singIn);
}
