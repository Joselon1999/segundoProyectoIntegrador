package utp.integrador.avance.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utp.integrador.avance.model.Producto;

public interface ProductRepository extends JpaRepository<Producto,Long> {

    Page<Producto> findAllByOrderByFechaVencimientoAsc(PageRequest e);

    @Query("SELECT p FROM Producto p WHERE p.cantidad > 0.0 ORDER BY p.fechaVencimiento ASC")
    Page<Producto> findProductosConStock(Double cantidad,PageRequest e);

    Page<Producto> findByCategoriaIdOrderByFechaVencimientoAsc(Long categoriaId,PageRequest e);

    @Query("SELECT p FROM Producto p " +
            "WHERE p.categoria.id = :categoriaId " +
            "AND p.cantidad > :cantidad " +
            "ORDER BY p.fechaVencimiento ASC")
    Page<Producto> findProductosSinStock(@Param("categoriaId") Long categoriaId, @Param("cantidad") Double cantidad, Pageable pageable);
}
