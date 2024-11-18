package utp.integrador.avance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String end() {
        return "loginPage";
    }

    /*@GetMapping
    public String end(Model model) {
        model.addAttribute("allUserlist", adminService.getAllUsers());
        model.addAttribute("allPuestolist", adminService.listPuestos());
        return "loginPage";
    }


    @GetMapping("/personal/{userId}")
    public String getUserPersonalData(@PathVariable int userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("personales", adminService.getUserDatosPersonales(userId));
        return "listPersonales";
    }

    @GetMapping("/estudio/{email}")
    public String getUserStudieData(@PathVariable String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("allemplist", adminService.findAllByUserEmail(email));
        return "listStudies";
    }


    @GetMapping("/experiencia/{email}")
    public String getUserExperiencieData(@PathVariable String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("allemplist", adminService.getAllTrabajo(email));
        return "listExperience";
    }

    @GetMapping("/aprobar/{email}")
    public String aprobeUser(@PathVariable String email, Model model) {
        adminService.updateUserStatus(email,"Aprobado");
        model.addAttribute("allemplist", adminService.getAllUsers());
        return "redirect:/admin";
    }

    @GetMapping("/rechazar/{email}")
    public String rejectUser(@PathVariable String email, Model model) {
        adminService.updateUserStatus(email,"Rechazado");
        model.addAttribute("allemplist", adminService.getAllTrabajo(email));
        return "redirect:/admin";
    }*/
}
