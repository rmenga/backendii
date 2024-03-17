package com.ruth.servicex.categoria.controller;

import com.ruth.servicex.categoria.domain.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ruth.servicex.categoria.sevice.CategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return new ResponseEntity<>(categoriaService.listarCategorias(), HttpStatus.OK);
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        return categoriaService.procurarCategoria(categoriaId)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.salvarCategoria(categoria), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        categoriaService.deletarCategoria(categoriaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("{categoriaId}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer categoriaId,
                                                        @RequestBody Categoria categoria) {

        if(categoriaService.procurarCategoria(categoriaId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)    ;
        }

        categoria.setIdCategoria(categoriaId);

        return new ResponseEntity<>(categoriaService.salvarCategoria(categoria), HttpStatus.OK);
    }
}
