package Saludity.User.Controller;

import Saludity.User.Model.Pojo.User;
import Saludity.User.Model.Request.CreateUserRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import Saludity.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0")
@Slf4j
@Tag(name = "User Controller", description = "Microservicio para gestionar datos de usuarios")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getUsers();

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        if (createdUser != null) {
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
