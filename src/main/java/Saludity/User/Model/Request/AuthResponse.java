package Saludity.User.Model.Request;

import lombok.*;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse
    {
        private String token;
}
