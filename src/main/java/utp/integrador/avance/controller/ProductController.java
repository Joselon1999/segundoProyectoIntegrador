package utp.integrador.avance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.ProductService;
import utp.integrador.avance.service.UserService;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping("/gestion-productos")
    public String gestionProductos(@RequestParam(defaultValue = "1") int pagina,
                                  @RequestParam(defaultValue = "5") int tamanio,
                                  Model model) {
        Page<Producto> list = productService.listProductos(pagina, tamanio);
        model.addAttribute("alimentos", list.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", list.getTotalPages());
        return "mainProductos";
    }
}
