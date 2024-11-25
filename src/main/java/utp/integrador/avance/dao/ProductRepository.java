package utp.integrador.avance.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.Producto;

public interface ProductRepository extends JpaRepository<Producto,Long> {

    Page<Producto> findAllByOrderByFechaVencimientoAsc(PageRequest e);

    Page<Producto> findByCategoriaIdOrderByFechaVencimientoAsc(Long categoriaId,PageRequest e);
}
