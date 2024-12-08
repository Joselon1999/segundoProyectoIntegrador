package utp.integrador.avance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.UserRepository;
import utp.integrador.avance.dto.UseUserRequest;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> getUsuario(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<Usuario> listUsuario(int pagina, int tamanio) {
        return userRepository.findAll(PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        usuario.setUsername(usuario.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return userRepository.save(usuario);
    }

    @Override
    public Usuario updateUser(UseUserRequest request) {
        Optional<Usuario> u = userRepository.findByEmail(request.getEmail());
        Usuario usuario = new Usuario();

        if (u.isPresent()) {
            usuario = u.get();
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
            usuario.setEnabled(request.isEnabled());
            userRepository.save(usuario);
        } else {
            log.warn("Request modificado: {} - {}",request.getEmail(),request.isEnabled());
        }
        return usuario;
    }

    @Override
    public Usuario updateUser(Usuario usuario) {
        return userRepository.save(usuario);
    }
}
