package com.ruth.servicex;

import com.ruth.servicex.categoria.domain.Categoria;
import com.ruth.servicex.categoria.sevice.CategoriaService;
import com.ruth.servicex.exceptios.NomeCategoriaJaExistenteException;
import com.ruth.servicex.exceptios.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class categoriaservicetest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    void registroCategoria_Sucesso() {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("Nova Categoria");

        Categoria result = categoriaService.criarCategoria(categoria);

        assertNotNull(result);
        assertEquals(categoria.getNomeCategoria(), result.getNomeCategoria());
    }

    @Test
    void registroCategoria_FalhaNomeDuplicado() {
        Categoria existente = new Categoria();
        existente.setNomeCategoria("Categoria X");
        categoriaService.criarCategoria(existente);

        Categoria nomeDuplicado = new Categoria();
        nomeDuplicado.setNomeCategoria(existente.getNomeCategoria());

        assertThrows(NomeCategoriaJaExistenteException.class, () -> categoriaService.criarCategoria(nomeDuplicado));
    }

    @Test
    void buscarCategoria_FalhaIdInexistente() {
        assertThrows(ObjectNotFoundException.class, () -> categoriaService.buscarCategoriaPorId(Integer.MAX_VALUE ));
    }
}