package utp.integrador.avance.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String viewHomePage(Model model) {

        return "index";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        return "login";
    }


    @PostMapping("/login")
    public String login(Model m) {
        return "index";
    }

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        return "/admin/dashboard";
    }

    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        return "login";
    }
}
