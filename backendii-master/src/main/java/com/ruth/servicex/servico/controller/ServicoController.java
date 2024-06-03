package com.ruth.servicex.servico.controller;

import com.ruth.servicex.servico.domain.Servico;
import com.ruth.servicex.servico.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Manter Servicos", description = "Funcionalidade das operações de Servico")
public class ServicoController {

    private ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Operation(summary = "Listar Servico", description = "O recurso lista todos os servicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        return new ResponseEntity<>(servicoService.listarServicos(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar Servico", description = "O recurso busca um servico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{servicoId}")
    public ResponseEntity<Servico> buscarServicos(@PathVariable("servicoId") Integer servicoId) {
        return servicoService.procurarServico(servicoId)
                .map(servico -> new ResponseEntity<>(servico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Criar Servico", description = "O recurso permite criar um servico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Servico> criarServicos(@RequestBody Servico servico) {
        return new ResponseEntity<>(servicoService.salvarServico(servico), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletar Servico", description = "O recurso deleta um servico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{servicoId}")
    public ResponseEntity<Void> excluirServicos(@PathVariable("servicoId") Integer servicoId) {
        servicoService.deletarServico(servicoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualizar Servico", description = "O recurso atualiza um servico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
    })
    @PreAuthorize("hasRole('ADMIN')")
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
