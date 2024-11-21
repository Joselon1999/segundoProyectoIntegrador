package utp.integrador.avance.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.service.ProductService;
import java.util.List;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping("/helper/gestion-productos")
    public String gestionProductos(@RequestParam(defaultValue = "1") int pagina,
                                  @RequestParam(defaultValue = "5") int tamanio,
                                  Model model) {
        Page<Producto> list = productService.listProductos(pagina, tamanio);
        model.addAttribute("alimentos", list.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", list.getTotalPages());
        return "mainProductos";
    }
    @GetMapping("/admin/gestion-productos/alimentos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alimento", new Producto());
        model.addAttribute("donantes", List.of("Donante 1", "Donante 2", "Donante 3"));
        return "createProducto";
    }

    @PostMapping("/admin/gestion-productos/alimentos/nuevo")
    public String saveEstudio(@Valid @ModelAttribute("alimento") Producto producto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "createProducto";
        }
        productService.createProducto(producto);
        return "redirect:/helper/gestion-productos";
    }
}
