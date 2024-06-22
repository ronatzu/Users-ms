package Saludity.User.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//Ejemplo API REST
    @GetMapping({"/hello","hw","hola"}) // Asi se puede asignar varios nombres
    public String hello(){
        System.out.println("SE IMPRIME");
        return "Hello World";
    }

}
