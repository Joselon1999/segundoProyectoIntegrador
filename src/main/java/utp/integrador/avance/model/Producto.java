package utp.integrador.avance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long id_producto;

    private String desc_producto;

    private int cant_producto;

    private LocalDate fecha_ingreso;

    private LocalDate fecha_vencimiento;

    private String estado_producto;

    @ManyToOne
    private Usuario usuario;
}
