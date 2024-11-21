package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.Donador;

public interface DonanteRepository extends JpaRepository<Donador,Long> {
}
