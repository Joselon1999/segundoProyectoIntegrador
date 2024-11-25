package utp.integrador.avance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.integrador.avance.dao.CategoryRepository;
import utp.integrador.avance.model.Categoria;
import utp.integrador.avance.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Categoria> listCategoria() {
        return categoryRepository.findAll();
    }
}
