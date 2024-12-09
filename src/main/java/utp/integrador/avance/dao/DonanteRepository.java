package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.Donador;

import java.util.Optional;

public interface DonanteRepository extends JpaRepository<Donador,Long> {

    Optional<Donador> findByNombre(String nombre);
}
