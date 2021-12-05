package rnd.mate00.springformlogin.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rnd.mate00.springformlogin.user.CustomUserDetails;

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
    public String restricted(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        System.out.println("Restricted view");

        model.addAttribute("loggedName", user.getUsername());

        return "restricted";
    }

//    @GetMapping("/handleLogin") // <- don't need that
//    public String handleLogin() {
//        System.out.println("Handling login");
//
//        return "redirect:/restricted"; // <- don't need that, it's set up in security config
//    }
}
