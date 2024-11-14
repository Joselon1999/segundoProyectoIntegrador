package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "donAlimentaria")
@Getter
@Setter
@NoArgsConstructor
public class DonAlimentaria {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long idDonAlimentaria;

    @ManyToOne
    @JoinColumn(name = "idDonacion", nullable = false)
    private Donacion donacion;

    private String descDonAlimentaria;
}
