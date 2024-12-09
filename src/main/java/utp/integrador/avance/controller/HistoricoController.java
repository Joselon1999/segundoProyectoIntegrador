package utp.integrador.avance.controller;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utp.integrador.avance.dao.HistoricoAlimentariaRepository;
import utp.integrador.avance.dao.HistoricoDonacionRepository;
import utp.integrador.avance.model.HistoricoAlimentaria;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.util.UserPDFExporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/helper/informes")
public class HistoricoController {

    @Autowired
    private HistoricoAlimentariaRepository alimentariaRepository;

    @Autowired
    private HistoricoDonacionRepository donacionRepository;

    @GetMapping()
    public String gestionDonacion(Model model) {

        model.addAttribute("historicoAlimentaria", alimentariaRepository.findAll());
        model.addAttribute("historicoDonacion", donacionRepository.findAll());
        return "informes";
    }
}
