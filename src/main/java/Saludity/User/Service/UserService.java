package Saludity.User.Service;

import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.CreateUserRequest;

public interface UserService {
    User login(String email, String password);
    User resetPassword(String email);
    User createUser(CreateUserRequest request);
    Boolean deleteUser(Long id);
}
