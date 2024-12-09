package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "donacion")
@Getter
@Setter
@NoArgsConstructor
public class Donacion {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long idDonacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Donador donador;
    private LocalDate fechaDonacion;
    private int tipoDonacion;
}
