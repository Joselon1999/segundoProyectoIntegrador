package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "historicoDonacion")
@Getter
@Setter
@NoArgsConstructor
public class HistoricoDonacion {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long id;

    @ManyToOne
    private DonMonetaria donMonetaria;
    private String cantidad;
    private LocalDate fecha_uso;
}
