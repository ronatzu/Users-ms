package Saludity.User.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {


    @GetMapping(value = "demo")
    public String welcome()
    {
        System.out.println("Se inicio demo");
        return "Welcome from secure endpoint";
    }
}