package utp.integrador.avance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "donador")
@Getter
@Setter
@NoArgsConstructor
public class Donador {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long id_donador;

    private String nombre;
}
