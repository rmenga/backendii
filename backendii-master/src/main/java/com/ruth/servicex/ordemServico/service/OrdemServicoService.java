package com.ruth.servicex.ordemServico.service;

import com.ruth.servicex.ordemServico.domain.OrdemServico;
import com.ruth.servicex.ordemServico.repository.OrdemServicoRepository;
import com.ruth.servicex.pagamento.domain.PagamentoBoleto;
import com.ruth.servicex.pagamento.service.BoletoService;
import com.ruth.servicex.servico.domain.Servico;
import com.ruth.servicex.servico.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional
    public OrdemServico criarOrdemServico(OrdemServico ordemServico) {
        try {
            Set<Servico> servicos = new HashSet<>();
            for (Servico se : ordemServico.getServicos()) {
                Optional<Servico> servicoOptional = servicoRepository.findById(se.getIdServico());
                servicoOptional.ifPresent(servicos::add);
            }
            ordemServico.setServicos(servicos);
            ordemServico.getPagamento().setOrdemServico(ordemServico);

            ordemServico.calcularValor();

            if (ordemServico.getPagamento() instanceof PagamentoBoleto) {
                boletoService.preencherBoleto(
                        (PagamentoBoleto) ordemServico.getPagamento(), ordemServico.getDataSolicitacao().toLocalDate()
                );
            }

            return ordemServicoRepository.save(ordemServico);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Dados Inválidos! Verifique e informe os dados corretamente!");
        }
    }

    public Optional<OrdemServico> buscarOrdemServicoPorId(Integer idOrdemServico) {
        return ordemServicoRepository.findById(idOrdemServico);
    }

    public List<OrdemServico> listarOrdemServico() {
        return ordemServicoRepository.findAll();
    }

    @Transactional
    public OrdemServico atualizarOrdemServico(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    @Transactional
    public void deletarOrdemServico(Integer idOrdemServico) {
        ordemServicoRepository.deleteById(idOrdemServico);
    }
}
