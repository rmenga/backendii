package com.ruth.servicex.ordemServico.repository;

import com.ruth.servicex.ordemServico.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServiceRepository extends JpaRepository<Integer, OrdemServico> {
}
