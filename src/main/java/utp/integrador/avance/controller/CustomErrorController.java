package utp.integrador.avance.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Obtén el código de estado HTTP
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode != null && statusCode == 403) {
            return "error-403";
        }

        model.addAttribute("statusCode", statusCode);
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
