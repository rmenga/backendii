package com.ruth.servicex.ordemServico.controller;

import com.ruth.servicex.ordemServico.domain.OrdemServico;
import com.ruth.servicex.ordemServico.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ordemservicos")
@Tag(name = "Manter Ordens Servico", description = "Funcionalidade das operações de Ordens Servico")
public class OrdemServicoResource {

    private OrdemServicoService ordemServicoService;

    public OrdemServicoResource(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @Operation(summary = "Criar Ordem de Servico", description = "O recurso permite criar uma ordem de servico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdemServico(@RequestBody OrdemServico ordemServico){
       ordemServicoService.criarOrdemServico(ordemServico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ordemServico.getIdOrdemServico()).toUri();
        return ResponseEntity.created(uri).build();
       // return new ResponseEntity<>(os, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar Ordens de Servico", description = "O recurso lista todas as ordens de servico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdemServico() {
        List<OrdemServico> os = ordemServicoService.listarOrdemServico();
        return new ResponseEntity<>(os, HttpStatus.OK);
    }

    @Operation(summary = "Buscar Ordem de Servico", description = "O recurso busca uma ordem de servico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{idOrdemServico}")
    public ResponseEntity<OrdemServico> buscarOrdemServico(@PathVariable Integer idOrdemServico) {
        return ordemServicoService.buscarOrdemServicoPorId(idOrdemServico)
                .map(os -> new ResponseEntity<>(os, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualizar Ordem de Servico", description = "O recurso atualiza uma ordem de servico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idOrdemServico}")
    public ResponseEntity<OrdemServico> atualizarOrdemServico(@PathVariable Integer idOrdemServico, @RequestBody OrdemServico os) {
        if (!ordemServicoService.buscarOrdemServicoPorId(idOrdemServico).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        os.setIdOrdemServico(idOrdemServico);
        OrdemServico osAtualizada = ordemServicoService.atualizarOrdemServico(os);
        return new ResponseEntity<>(osAtualizada, HttpStatus.OK);
    }

    @Operation(summary = "Deletar Ordem de Servico", description = "O recurso deleta uma Ordem de Servico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idOrdemServico}")
    public ResponseEntity<Void> deletarOrdemServico(@PathVariable Integer idOrdemServico) {
        ordemServicoService.deletarOrdemServico(idOrdemServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}