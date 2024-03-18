package com.ruth.servicex.servico.controller;

import com.ruth.servicex.servico.domain.Servico;
import com.ruth.servicex.servico.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        return new ResponseEntity<>(servicoService.listarServicos(), HttpStatus.OK);
    }

    @GetMapping("/{servicoId}")
    public ResponseEntity<Servico> buscarServicos(@PathVariable("servicoId") Integer servicoId) {
        return servicoService.procurarServico(servicoId)
                .map(servico -> new ResponseEntity<>(servico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Servico> criarServicos(@RequestBody Servico servico) {
        return new ResponseEntity<>(servicoService.salvarServico(servico), HttpStatus.CREATED);
    }

    @DeleteMapping("/{servicoId}")
    public ResponseEntity<Void> excluirServicos(@PathVariable("servicoId") Integer servicoId) {
        servicoService.deletarServico(servicoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{servicoId}")
    public ResponseEntity<Servico> atualizarServicos(@PathVariable("servicoId") Integer servicoId,
                                                        @RequestBody Servico servico) {

        if(servicoService.procurarServico(servicoId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)    ;
        }

        servico.setIdServico(servicoId);

        return new ResponseEntity<>(servicoService.salvarServico(servico), HttpStatus.OK);
    }
}
