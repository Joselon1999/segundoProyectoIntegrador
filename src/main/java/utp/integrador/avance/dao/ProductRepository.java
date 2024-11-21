package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.Producto;

public interface ProductRepository extends JpaRepository<Producto,Long> {
}
