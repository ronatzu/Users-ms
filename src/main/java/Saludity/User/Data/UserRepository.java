package Saludity.User.Data;

import Saludity.User.Model.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String emailAddress);

}
