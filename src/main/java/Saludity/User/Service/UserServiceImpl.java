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
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User login(String email, String password){
        Optional<User> user =userRepository.findByEmail(email);
        if (user.isPresent()){
            if (user.get().getPassword().equals(password)) {
                return user.orElse(null);
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
                    .build();

            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public Boolean removeUser(Long id) {
        User user = userRepository.getById(Long.valueOf(id));
        System.out.println(user);
        if (user != null) {
            userRepository.delete(user);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }
}




