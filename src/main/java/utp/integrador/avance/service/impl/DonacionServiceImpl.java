package utp.integrador.avance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.DonMonetariaRepository;
import utp.integrador.avance.dao.DonacionRepository;
import utp.integrador.avance.dao.DonanteRepository;
import utp.integrador.avance.dao.UserRepository;
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
        return donMonetariaRepository.save(donMonetaria);
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
        } else {
            log.warn("Request modificado: {} - {}",request.getProductId(),request.getCantidad());
        }
        return d;
    }
}
