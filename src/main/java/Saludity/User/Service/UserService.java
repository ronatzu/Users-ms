package Saludity.User.Service;

import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.CreateUserRequest;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User login(String email, String password);
    User resetPassword(String email);
    User createUser(CreateUserRequest request);
    Boolean removeUser(Long id);



}
