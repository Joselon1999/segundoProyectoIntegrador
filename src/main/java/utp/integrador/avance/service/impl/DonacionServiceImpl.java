package utp.integrador.avance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.DonMonetariaRepository;
import utp.integrador.avance.dao.DonacionRepository;
import utp.integrador.avance.dao.DonanteRepository;
import utp.integrador.avance.dao.UserRepository;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Donacion;
import utp.integrador.avance.model.Donador;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.DonacionService;

import java.time.LocalDate;
import java.util.Set;

@Service
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
        return donMonetariaRepository.findAll(PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public DonMonetaria createDonacion(DonMonetaria donMonetaria) {
        Donacion donacion = new Donacion();
        donacion.setDonador(donanteRepository.findById(1L).orElse(new Donador()));
        donacion.setUsuario(userRepository.findById(1L).orElse(new Usuario()));
        donacion.setTipoDonacion(1);
        donacion.setFechaDonacion(LocalDate.now());
        donMonetaria.setDonacion(donacion);

        donacionRepository.save(donacion);
        donMonetariaRepository.save(donMonetaria);
        return donMonetaria;
    }
}
