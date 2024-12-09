package utp.integrador.avance.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.DonMonetaria;

import java.math.BigDecimal;

public interface DonMonetariaRepository extends JpaRepository<DonMonetaria,Long> {

    Page<DonMonetaria> findByMontoDonacionGreaterThan(BigDecimal cantidad, Pageable pageable);
}
