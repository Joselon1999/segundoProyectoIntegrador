package utp.integrador.avance.controller;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import utp.integrador.avance.dao.HistoricoAlimentariaRepository;
import utp.integrador.avance.dao.HistoricoDonacionRepository;
import utp.integrador.avance.model.Producto;

@Controller
@Slf4j
public class TransparenciaController {

    @Autowired
    private HistoricoAlimentariaRepository alimentariaRepository;

    @Autowired
    private HistoricoDonacionRepository donacionRepository;

    @GetMapping("/portal-transparencia")
    public String mostrarFormulario(Model model) {

        model.addAttribute("historicoAlimentaria", alimentariaRepository.findAll());
        model.addAttribute("historicoDonacion", donacionRepository.findAll());
        return "portalTransparencia";
    }
}
