package utp.integrador.avance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
