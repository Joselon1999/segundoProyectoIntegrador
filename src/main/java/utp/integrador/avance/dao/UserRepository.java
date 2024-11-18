package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.Usuario;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String email);
}