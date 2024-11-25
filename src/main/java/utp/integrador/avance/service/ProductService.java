package utp.integrador.avance.service;

import org.springframework.data.domain.Page;
import utp.integrador.avance.model.Producto;

public interface ProductService {

    Page<Producto> listProductos(int pagina, int tamanio);

    Producto createProducto(Producto producto);

    Page<Producto> listarPorCategoria(Long categoria,int pagina, int tamanio);
}
