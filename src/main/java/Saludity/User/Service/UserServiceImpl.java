package Saludity.User.Service;

import Saludity.User.Data.UserRepository;
import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

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
        if (request != null && StringUtils.hasLength(request.getEmail().trim())
                && StringUtils.hasLength(request.getPassword().trim()))
        {

            User user = User.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .active(request.isActive())
                    .build();

            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteUser(Long id) {
        return null;
    }
}




