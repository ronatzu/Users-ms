package Saludity.User.Service;

import Saludity.User.Data.UserRepository;
import Saludity.User.Model.Pojo.Rolee;
import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.AuthResponse;
import Saludity.User.Model.Request.LoginRequest;
import Saludity.User.Model.Request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = userRepository.findByEmail(request.getEmail()).orElse(null);
        String token = jwtService.generateToken(userDetails);

        System.out.println("se logeo el usuario " + userDetails.getUsername());
        return new AuthResponse(token);

    }

    public AuthResponse register(RegisterRequest request) {
        User user =User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
//                .rolee(Rolee.USER)
                .build();

        userRepository.save(user);
        System.out.println("Se creo el usuario"+user.getEmail());
        return AuthResponse.builder()
                .token(jwtService.generateToken(user)).build();
    }


}
