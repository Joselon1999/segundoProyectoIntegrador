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
import utp.integrador.avance.dto.UseProductRequest;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.DonacionService;
import utp.integrador.avance.service.UserService;
import utp.integrador.avance.service.session.IAuthenticationFacade;

import java.util.List;

@Controller
@Slf4j
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @GetMapping("/helper/gestion-donacion")
    public String gestionDonacion(@RequestParam(defaultValue = "1") int pagina,
                                   @RequestParam(defaultValue = "5") int tamanio,
                                   Model model) {
        Page<DonMonetaria> list = donacionService.listarDonacion(pagina,tamanio);

        model.addAttribute("donaciones", list);
        model.addAttribute("totalPaginas", list.getTotalPages());
        model.addAttribute("paginaActual", pagina);
        return "mainDonacion";
    }

    @GetMapping("/admin/gestion-donacion/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("donacion", new DonMonetaria());
        model.addAttribute("donantes", donanteRepository.findAll());
        return "createDonacion";
    }

    @PostMapping("/admin/gestion-donacion/nuevo")
    public String registrarAlimento(@Valid @ModelAttribute("donacion") DonMonetaria donMonetaria,
                                    BindingResult bindingResult,
                                    Model model) {
        Usuario u = userService.getUsuario(authenticationFacade.getAuthentication().getName()).get();
        donMonetaria.setUsuario(u);
        if (bindingResult.hasErrors()) {
            model.addAttribute("donacion", new DonMonetaria());
            model.addAttribute("donantes", donanteRepository.findAll());
            return "createDonacion";
        }
        donacionService.createDonacion(donMonetaria);
        return "redirect:/helper/gestion-donacion";
    }

    @GetMapping("/helper/gestion-donacion/{id}/usar")
    public String usarDonacion(@PathVariable Long id, Model model) {
        UseDonacionRequest request = new UseDonacionRequest();
        request.setProductId(id);

        model.addAttribute("donacion", donacionService.getDonacion(id));
        model.addAttribute("donantes", donanteRepository.findAll());
        model.addAttribute("request", request);
        return "usarDonacion";
    }

    @PostMapping("/helper/gestion-donacion/usar")
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
        return "redirect:/helper/gestion-donacion";
    }
}
