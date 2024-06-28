package Saludity.User.Model.Request;

import Saludity.User.Model.Pojo.Profile;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;

}
