package utp.integrador.avance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.*;
import utp.integrador.avance.dto.UseDonacionRequest;
import utp.integrador.avance.model.*;
import utp.integrador.avance.service.DonacionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DonacionServiceImpl implements DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private DonMonetariaRepository donMonetariaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoricoDonacionRepository historicoDonacionRepository;

    @Override
    public Page<DonMonetaria> listarDonacion(int pagina, int tamanio) {
        return donMonetariaRepository.findByMontoDonacionGreaterThan(
                BigDecimal.ZERO,PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public DonMonetaria createDonacion(DonMonetaria donMonetaria) {
        donMonetaria.setDonador(donanteRepository.findById(donMonetaria.getDonador().getId_donador()).orElse(new Donador()));
        donMonetaria.setUsuario(userRepository.findById(donMonetaria.getDonador().getId_donador()).orElse(new Usuario()));
        donMonetaria.setFechaDonacion(LocalDate.now());
        DonMonetaria d = donMonetariaRepository.save(donMonetaria);

        HistoricoDonacion h = new HistoricoDonacion();
        h.setDonMonetaria(d);
        h.setFecha_uso(LocalDate.now());
        h.setCantidad(String.valueOf(donMonetaria.getMontoDonacion()));
        historicoDonacionRepository.save(h);
        return d;
    }

    @Override
    public Optional<DonMonetaria> getDonacion(Long id) {
        return donMonetariaRepository.findById(id);
    }

    @Override
    public DonMonetaria usarDonacion(UseDonacionRequest request) {
        Optional<DonMonetaria> producto = donMonetariaRepository.findById(request.getProductId());
        DonMonetaria d = new DonMonetaria();

        if (producto.isPresent()) {
            d = producto.get();
            d.setMontoDonacion(d.getMontoDonacion().subtract(request.getCantidad()));
            donMonetariaRepository.save(d);

            HistoricoDonacion h = new HistoricoDonacion();
            h.setDonMonetaria(d);
            h.setFecha_uso(LocalDate.now());
            h.setCantidad("-".concat(String.valueOf(request.getCantidad())));
            historicoDonacionRepository.save(h);
        } else {
            log.warn("Request modificado: {} - {}",request.getProductId(),request.getCantidad());
        }
        return d;
    }
}
