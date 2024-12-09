package utp.integrador.avance.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UseDonacionRequest {

    private Long productId;
    private BigDecimal cantidad;
    private LocalDate fechaVencimiento;
}
