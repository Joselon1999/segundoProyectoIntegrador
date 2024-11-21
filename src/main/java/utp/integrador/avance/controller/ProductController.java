package utp.integrador.avance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utp.integrador.avance.model.Donador;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.ProductService;
import utp.integrador.avance.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/gestion-productos")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping()
    public String gestionProductos(@RequestParam(defaultValue = "1") int pagina,
                                  @RequestParam(defaultValue = "5") int tamanio,
                                  Model model) {
        Page<Producto> list = productService.listProductos(pagina, tamanio);
        model.addAttribute("alimentos", list.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", list.getTotalPages());
        return "mainProductos";
    }
    @GetMapping("/alimentos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alimento", new Producto());
        model.addAttribute("donantes", List.of("Donante 1", "Donante 2", "Donante 3"));
        return "createProducto";
    }

    @PostMapping("/alimentos/nuevo")
    public String saveEstudio(Model model,@ModelAttribute("alimento") Producto producto) {
        productService.createProducto(producto);
        return "redirect:/gestion-productos";
    }
}
