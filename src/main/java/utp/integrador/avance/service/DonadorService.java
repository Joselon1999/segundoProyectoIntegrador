package utp.integrador.avance.service;

import org.springframework.data.domain.Page;
import utp.integrador.avance.dto.UseDonacionRequest;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Donador;

import java.util.Optional;

public interface DonadorService {

    Page<Donador> listarDonador(int pagina, int tamanio);

    Donador createDonador(Donador donador);

    Optional<Donador> getDonador(String nombre);

    Donador usarDonador(Donador donador);
}
