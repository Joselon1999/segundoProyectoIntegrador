package utp.integrador.avance.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import utp.integrador.avance.dto.UseUserRequest;
import utp.integrador.avance.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<Usuario> getUsuario(String email);
    Page<Usuario> listUsuario(int pagina, int tamanio);

    Usuario createUser(Usuario usuario);

    Usuario updateUser(UseUserRequest request);
}
