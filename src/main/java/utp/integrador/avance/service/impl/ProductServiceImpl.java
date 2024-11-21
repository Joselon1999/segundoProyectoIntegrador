package utp.integrador.avance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.ProductRepository;
import utp.integrador.avance.model.Producto;
import utp.integrador.avance.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Producto> listProductos(int pagina, int tamanio) {
        return productRepository.findAll(PageRequest.of(pagina-1,tamanio));
    }
}
