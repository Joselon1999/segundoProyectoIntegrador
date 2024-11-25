package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.Categoria;

public interface CategoryRepository extends JpaRepository<Categoria,Long> {
}
