package utp.integrador.avance.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UseUserRequest {

    private String email;
    private String password;
    private boolean enabled;
}
