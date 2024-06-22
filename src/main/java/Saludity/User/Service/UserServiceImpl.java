package Saludity.User.Service;

import Saludity.User.Data.UserRepository;
import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String email, String password){
        User user =userRepository.findByEmail(email);
        if (user!=null){
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }

    @Override
    public User resetPassword(String email) {
        return null;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return null;
    }


}
