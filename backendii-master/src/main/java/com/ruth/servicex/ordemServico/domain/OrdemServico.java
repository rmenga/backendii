package com.ruth.servicex.ordemServico.domain;

import com.ruth.servicex.pagamento.domain.Pagamento;
import com.ruth.servicex.servico.domain.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDEMDESERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID_ORDEM_SERVICO")
    private Integer idOrdemServico;

    @Column(name = "DATA_SOLICITACAO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataSolicitacao;

    @Column(name = "VALOR_ORDEM_SERVICO")
    private Double valorOrdemServico;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ordemServico")
    private Pagamento pagamento;

    @ManyToMany
    @JoinTable(name = "SERVICO_OS", joinColumns = @JoinColumn(name = "ID_OS"), inverseJoinColumns = @JoinColumn(name = "ID_SERVICO"))
    private Set<Servico> servico;
}