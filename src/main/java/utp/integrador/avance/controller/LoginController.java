package utp.integrador.avance.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import utp.integrador.avance.dao.TokenRepository;
import utp.integrador.avance.dto.PasswordResetToken;
import utp.integrador.avance.dto.UserDTO;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.UserService;
import utp.integrador.avance.service.impl.UserDetailsServiceImpl;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenRepository tokenRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassordProcess(@ModelAttribute UserDTO userDTO) {
        String output = "";
        Usuario user = userService.getUsuario(userDTO.getEmail()).get();
        if (user != null) {
            output = userDetailsService.sendEmail(user);
        }
        if (output.equals("success")) {
            return "redirect:/forgotPassword?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        PasswordResetToken reset = tokenRepository.findByToken(token);
        if (reset != null && userDetailsService.hasExipred(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getUser().getEmail());
            return "resetPassword";
        }
        return "redirect:/forgotPassword?error";
    }

    @PostMapping("/resetPassword")
    public String passwordResetProcess(@ModelAttribute UserDTO userDTO) {
        Usuario user = userService.getUsuario(userDTO.getEmail()).get();
        if(user != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userService.updateUser(user);
        }
        return "redirect:/login";
    }
}
