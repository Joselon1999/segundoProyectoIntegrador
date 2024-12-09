package utp.integrador.avance.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utp.integrador.avance.dao.DonanteRepository;
import utp.integrador.avance.dto.UseDonacionRequest;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Donador;
import utp.integrador.avance.service.DonacionService;
import utp.integrador.avance.service.DonadorService;

@Controller
@Slf4j
public class DonadorController {


    @Autowired
    private DonadorService donadorService;


    @GetMapping("/helper/gestion-donador")
    public String gestionDonacion(@RequestParam(defaultValue = "1") int pagina,
                                   @RequestParam(defaultValue = "5") int tamanio,
                                   Model model) {
        Page<Donador> list = donadorService.listarDonador(pagina,tamanio);

        model.addAttribute("donadores", list);
        model.addAttribute("totalPaginas", list.getTotalPages());
        model.addAttribute("paginaActual", pagina);
        return "mainDonadores";
    }

    @GetMapping("/helper/gestion-donador/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("donador", new Donador());
        return "createDonador";
    }

    @PostMapping("/helper/gestion-donador/nuevo")
    public String registrarAlimento(@Valid @ModelAttribute("donador") Donador donador,
                                    Model model) {
        donadorService.createDonador(donador);
        return "redirect:/helper/gestion-donador";
    }

/*    @GetMapping("/helper/gestion-donador/{id}/actualizar")
    public String usarDonacion(@PathVariable String nombre, Model model) {

        model.addAttribute("donacion", donadorService.getDonador(nombre));
        model.addAttribute("request", new Donador());
        return "usarDonacion";
    }

    @PostMapping("/helper/gestion-donador/usar")
    public String registrarUsoDonacion(@ModelAttribute("request") UseDonacionRequest request,
                                       BindingResult bindingResult,
                                       Model model) {
        DonMonetaria donMonetaria = donacionService.getDonacion(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Donacion no encontrado"));

        if (request.getCantidad().compareTo(donMonetaria.getMontoDonacion()) > 0) {
            model.addAttribute("donacion", donacionService.getDonacion(request.getProductId()));
            model.addAttribute("request", request);
            model.addAttribute("mensaje", "La cantidad solicitada supera al monto disponible.");
            return "usarDonacion";
        }
        donacionService.usarDonacion(request);
        return "redirect:/helper/gestion-donador";
    }*/
}
