package utp.integrador.avance.controller;


import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.UserService;
import utp.integrador.avance.util.UserPDFExporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/gestion-usuarios")
    public String gestionUsuarios(@RequestParam(defaultValue = "1") int pagina,
                                  @RequestParam(defaultValue = "5") int tamanio,
                                  Model model) {
        Page<Usuario> userList = userService.listUsuario(pagina, tamanio);
        model.addAttribute("usuarios", userList.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", userList.getTotalPages());
        return "mainUsuarios";
    }
}
