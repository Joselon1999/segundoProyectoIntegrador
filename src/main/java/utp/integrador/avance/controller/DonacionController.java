package utp.integrador.avance.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.service.DonacionService;

import java.util.List;

@Controller
@Slf4j
public class DonacionController {

    @Autowired
    private DonacionService donacionService;


    @GetMapping("/helper/gestion-donacion")
    public String gestionDonacion(@RequestParam(defaultValue = "1") int pagina,
                                   @RequestParam(defaultValue = "5") int tamanio,
                                   Model model) {

        model.addAttribute("donaciones", donacionService.listarDonacion(pagina,tamanio));
        return "mainDonacion";
    }

    @GetMapping("/admin/gestion-donacion/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("donacion", new DonMonetaria());
        model.addAttribute("donantes", List.of("Donante 1", "Donante 2", "Donante 3"));
        return "createDonacion";
    }

    @PostMapping("/admin/gestion-donacion/nuevo")
    public String registrarAlimento(@Valid @ModelAttribute("donacion") DonMonetaria donMonetaria,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("donacion", new DonMonetaria());
            model.addAttribute("donantes", List.of("Donante 1", "Donante 2", "Donante 3"));
            return "createDonacion";
        }
        donacionService.createDonacion(donMonetaria);
        return "redirect:/helper/gestion-donacion";
    }
}
