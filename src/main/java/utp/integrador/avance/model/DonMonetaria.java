package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    private String descDonMonetaria;

    @Column(precision = 20, scale = 2)
    private BigDecimal montoDonacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Donador donador;
    private LocalDate fechaDonacion;
}
