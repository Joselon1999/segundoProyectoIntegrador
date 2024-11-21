package utp.integrador.avance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.UserRepository;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario getUsuario(String email) {
        return null;
    }

    @Override
    public Page<Usuario> listUsuario(int pagina, int tamanio) {
        return userRepository.findAll(PageRequest.of(pagina-1,tamanio));
    }
}
