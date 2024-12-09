package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.DonMonetaria;
import utp.integrador.avance.model.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion,Long> {
}
