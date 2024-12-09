package utp.integrador.avance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.DonanteRepository;
import utp.integrador.avance.dao.HistoricoAlimentariaRepository;
import utp.integrador.avance.dao.ProductRepository;
import utp.integrador.avance.dao.UserRepository;
import utp.integrador.avance.dto.UseProductRequest;
import utp.integrador.avance.model.Donador;
import utp.integrador.avance.model.HistoricoAlimentaria;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.model.Usuario;
import utp.integrador.avance.service.ProductService;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoricoAlimentariaRepository historicoAlimentariaRepository;

    @Override
    public Page<Producto> listProductos(int pagina, int tamanio) {
        return productRepository.findProductosConStock(0.0,PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public Producto createProducto(Producto producto) {
        Donador d = donanteRepository.findById(producto.getDonador().getId_donador())
                .orElse(new Donador());
        producto.setDonador(d);

        Usuario u = userRepository.findById(producto.getUsuario().getId_usuario())
                .orElse(new Usuario());
        producto.setUsuario(u);

        producto.setFecha_ingreso(LocalDate.now());

        Producto p = productRepository.save(producto);

        HistoricoAlimentaria h = new HistoricoAlimentaria();
        h.setProducto(p);
        h.setFecha_uso(LocalDate.now());
        h.setCantidad(String.valueOf(producto.getCantidad()));
        historicoAlimentariaRepository.save(h);
        return p;
    }

    @Override
    public Page<Producto> listarPorCategoria(Long categoria, int pagina, int tamanio) {
        return productRepository.findProductosSinStock(
                categoria,0.0,PageRequest.of(pagina-1,tamanio));
    }

    @Override
    public Optional<Producto> getProducto(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Producto usarProducto(UseProductRequest request) {
        Optional<Producto> producto = productRepository.findById(request.getProductId());
        Producto p = new Producto();

        if (producto.isPresent()) {
            p = producto.get();
            p.setCantidad(p.getCantidad() - request.getCantidad());
            productRepository.save(p);

            HistoricoAlimentaria h = new HistoricoAlimentaria();
            h.setProducto(p);
            h.setFecha_uso(LocalDate.now());
            h.setCantidad(String.valueOf(request.getCantidad()));
            historicoAlimentariaRepository.save(h);
        } else {
            log.warn("Request modificado: {} - {}",request.getProductId(),request.getCantidad());
        }
        return p;
    }

    @Override
    public Producto actualizarProducto(UseProductRequest request) {
        Optional<Producto> producto = productRepository.findById(request.getProductId());
        Producto p = new Producto();

        if (producto.isPresent()) {
            p = producto.get();
            p.setCantidad((double) request.getCantidad());
            p.setFechaVencimiento(request.getFechaVencimiento());
            productRepository.save(p);
        } else {
            log.warn("Request modificado: {} - {}",request.getProductId(),request.getCantidad());
        }
        return p;
    }
}
