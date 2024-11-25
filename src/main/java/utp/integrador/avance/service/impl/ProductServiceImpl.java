package utp.integrador.avance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.DonanteRepository;
import utp.integrador.avance.dao.ProductRepository;
import utp.integrador.avance.dao.UserRepository;
import utp.integrador.avance.model.Donador;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.ProductService;

import java.time.LocalDate;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Producto> listProductos(int pagina, int tamanio) {
        return productRepository.findAllByOrderByFechaVencimientoAsc(PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public Producto createProducto(Producto producto) {
        Donador d = donanteRepository.findById(1L).orElse(new Donador());
        producto.setDonador(d);

        Usuario u = userRepository.findById(1L).orElse(new Usuario());
        producto.setUsuario(u);

        producto.setFecha_ingreso(LocalDate.now());
        return productRepository.save(producto);
    }

    @Override
    public Page<Producto> listarPorCategoria(Long categoria, int pagina, int tamanio) {
        return productRepository.findByCategoriaIdOrderByFechaVencimientoAsc(
                categoria,PageRequest.of(pagina-1,tamanio));
    }
}
