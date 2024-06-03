package com.ruth.servicex.servico.service;

import com.ruth.servicex.servico.domain.Servico;
import com.ruth.servicex.servico.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    private ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> procurarServico(Integer id) {
        return servicoRepository.findById(id);
    }

    @Transactional
    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Transactional
    public void deletarServico(Integer id) {
        servicoRepository.deleteById(id);
    }
}