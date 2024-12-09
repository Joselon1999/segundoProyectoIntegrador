package utp.integrador.avance.service;

import org.springframework.data.domain.Page;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Donacion;

public interface DonacionService {

    Page<DonMonetaria> listarDonacion(int pagina, int tamanio);

    DonMonetaria createDonacion(DonMonetaria donMonetaria);
}
