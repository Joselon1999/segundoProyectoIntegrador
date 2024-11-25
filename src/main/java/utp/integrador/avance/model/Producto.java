package utp.integrador.avance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "La cantidad debe ser un número positivo")
    private Double cant_producto;

    private LocalDate fecha_ingreso;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    private String estado_producto;

    @ManyToOne
    private Donador donador;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Categoria categoria;
}
