package utp.integrador.avance.service;

import org.springframework.security.core.userdetails.UserDetails;
import utp.integrador.avance.model.Usuario;

public interface UserService {

    UserDetails getUsuario(String email);
}
