package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "donMonetaria")
@Getter
@Setter
@NoArgsConstructor
public class DonMonetaria {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long idDonMonetaria;

    @ManyToOne
    @JoinColumn(name = "idDonacion", nullable = false)
    private Donacion donacion;

    private String descDonMonetaria;
    private double montoDonacion;
}
