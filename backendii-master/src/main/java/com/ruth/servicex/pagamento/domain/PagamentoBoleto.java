package com.ruth.servicex.pagamento.domain;


import com.ruth.servicex.ordemServico.domain.OrdemServico;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonTypeName("pagamentoBoleto")
public class PagamentoBoleto extends Pagamento {

    @Column(name = "DATA_VENCIMENTO")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
    @Column(name = "DATA_PAGAMENTO")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;

    public PagamentoBoleto(Integer idPagamento, StatusPagamento statusPagamento, OrdemServico ordemServico, LocalDate dataVencimento, LocalDate dataPagamento) {
        super(idPagamento, statusPagamento, ordemServico);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoBoleto(LocalDate dataVencimento, LocalDate dataPagamento) {
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}