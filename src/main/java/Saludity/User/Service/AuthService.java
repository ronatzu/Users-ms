package Saludity.User.Service;

import Saludity.User.Data.UserRepository;
import Saludity.User.Model.Pojo.Profile;
import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.AuthResponse;
import Saludity.User.Model.Request.LoginRequest;
import Saludity.User.Model.Request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user =User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profile(new Profile())
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.generateToken(user)).build();
    }
}
