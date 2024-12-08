package utp.integrador.avance.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UseProductRequest {

    private Long productId;
    private int cantidad;
    private LocalDate fechaVencimiento;
}
