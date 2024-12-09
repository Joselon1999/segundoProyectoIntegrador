package utp.integrador.avance.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UseProductRequest {

    private Long productId;
    private Double cantidad;
    private Boolean enabled;
    private LocalDate fechaVencimiento;
}
