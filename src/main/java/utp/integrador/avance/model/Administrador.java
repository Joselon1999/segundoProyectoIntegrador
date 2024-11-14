package utp.integrador.avance.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Usuario {
    private String nomUsuAdmin;
    private String detalleAdmin;
}
