package utp.integrador.avance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.integrador.avance.model.HistoricoAlimentaria;
import utp.integrador.avance.model.HistoricoDonacion;

public interface HistoricoDonacionRepository extends JpaRepository<HistoricoDonacion,Long> {
}
