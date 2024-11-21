package utp.integrador.avance.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import utp.integrador.avance.model.Usuario;

import java.util.List;

public interface UserService {

    Usuario getUsuario(String email);
    Page<Usuario> listUsuario(int pagina, int tamanio);
}
