package utp.integrador.avance.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.service.CategoryService;
import utp.integrador.avance.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/gestion-productos")
@Slf4j
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String gestionProductos(@RequestParam(defaultValue = "1") int pagina,
                                   @RequestParam(defaultValue = "5") int tamanio,
                                   @RequestParam(value = "categoria", required = false) Long idCategoria,
                                   Model model) {

        if (idCategoria != null) {
            Page<Producto> list = productService.listarPorCategoria(idCategoria,pagina, tamanio);
            model.addAttribute("alimentos", list.getContent());
            model.addAttribute("totalPaginas", list.getTotalPages());
            log.info("Categoria: {}",idCategoria);
        } else {
            Page<Producto> list = productService.listProductos(pagina, tamanio);
            model.addAttribute("alimentos", list.getContent());
            model.addAttribute("totalPaginas", list.getTotalPages());
            log.info("Sin Categoria");
        }

        model.addAttribute("paginaActual", pagina);
        model.addAttribute("categorias", categoryService.listCategoria());
        return "mainProductos";
    }
    @GetMapping("/alimentos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alimento", new Producto());
        model.addAttribute("donantes", List.of("Donante 1", "Donante 2", "Donante 3"));
        model.addAttribute("categorias", categoryService.listCategoria());
        return "createProducto";
    }

    @PostMapping("/alimentos/nuevo")
    public String saveEstudio(@Valid @ModelAttribute("alimento") Producto producto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("alimento", new Producto());
            model.addAttribute("donantes", List.of("Donante 1", "Donante 2", "Donante 3"));
            model.addAttribute("categorias", categoryService.listCategoria());
            return "createProducto";
        }
        productService.createProducto(producto);
        return "redirect:/admin/gestion-productos";
    }
}
