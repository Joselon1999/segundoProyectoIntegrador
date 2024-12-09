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
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Donador;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.DonacionService;
import utp.integrador.avance.service.DonadorService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class DonadorServiceImpl implements DonadorService {

    @Autowired
    private DonanteRepository donanteRepository;

    @Override
    public Page<Donador> listarDonador(int pagina, int tamanio) {
        return donanteRepository.findAll(PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public Donador createDonador(Donador donador) {
        return donanteRepository.save(donador);
    }

    @Override
    public Optional<Donador> getDonador(String nombre) {
        return donanteRepository.findByNombre(nombre);
    }

    @Override
    public Donador usarDonador(Donador donador) {
        Optional<Donador> producto = donanteRepository.findById(donador.getId_donador());
        Donador d = new Donador();

        if (producto.isPresent()) {
            d = producto.get();
            d.setNombre(donador.getNombre());
            donanteRepository.save(d);
        } else {
            log.warn("Request modificado: {} - {}",donador.getId_donador(),donador.getNombre());
        }
        return d;
    }
}
