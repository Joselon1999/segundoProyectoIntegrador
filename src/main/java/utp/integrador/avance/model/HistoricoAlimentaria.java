package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "historicoAlimentaria")
@Getter
@Setter
@NoArgsConstructor
public class HistoricoAlimentaria {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long id;

    @ManyToOne
    private Producto producto;
    private String cantidad;
    private LocalDate fecha_uso;
}
