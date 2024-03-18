package com.ruth.servicex.categoria.sevice;

import com.ruth.servicex.categoria.domain.Categoria;
import com.ruth.servicex.categoria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> procurarCategoria(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}