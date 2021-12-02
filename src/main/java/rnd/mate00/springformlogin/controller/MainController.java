package rnd.mate00.springformlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        System.out.println("Index view");

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/signin")
    public String signIn() {
        System.out.println("Sign-in view");

        return "signin";
    }

    @GetMapping("/restricted")
    public String restricted() {
        System.out.println("Restricted view");

        return "restricted";
    }

    @GetMapping("/handleLogin")
    public String handleLogin() {
        System.out.println("Handling login");

        return "redirect:/restricted";
    }
}
