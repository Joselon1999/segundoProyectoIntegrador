package utp.integrador.avance.controller;


import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.RolService;
import utp.integrador.avance.service.UserService;
import utp.integrador.avance.util.UserPDFExporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/gestion-usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;


    @GetMapping()
    public String gestionUsuarios(@RequestParam(defaultValue = "1") int pagina,
                                  @RequestParam(defaultValue = "5") int tamanio,
                                  Model model) {
        Page<Usuario> userList = userService.listUsuario(pagina, tamanio);
        model.addAttribute("usuarios", userList.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", userList.getTotalPages());
        return "mainUsuarios";
    }
    @GetMapping("/usuario/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarRoles());
        return "createUsuario";
    }

    @PostMapping("/usuario/nuevo")
    public String saveEstudio(@Valid @ModelAttribute("alimento") Usuario usuario,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "createUsuario";
        }
        userService.createUser(usuario);
        return "redirect:/admin/gestion-usuarios";
    }
}
