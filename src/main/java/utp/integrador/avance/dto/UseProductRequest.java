package utp.integrador.avance.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UseProductRequest {

    private Long productId;
    private int cantidad;
}
