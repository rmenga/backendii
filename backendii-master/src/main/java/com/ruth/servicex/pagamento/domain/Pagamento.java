package com.ruth.servicex.pagamento.domain;


import com.ruth.servicex.ordemServico.domain.OrdemServico;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {

    @Id
    private Integer idPagamento;

    @Column(name = "STATUS_PAGAMENTO")
    private Integer statusPagamento;

    @OneToOne
    @JoinColumn(name = "ID_OrdemServico")
    @MapsId
    private OrdemServico ordemServico;

    public Pagamento(Integer idPagamento, StatusPagamento statusPagamento, OrdemServico ordemServico) {
        this.idPagamento = idPagamento;
        this.statusPagamento = (statusPagamento == null) ? null: statusPagamento.getCod();
        this.ordemServico = ordemServico;
    }

    public Pagamento() {

    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return StatusPagamento.toEnum(statusPagamento);
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento.getCod();
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(idPagamento, pagamento.idPagamento) && Objects.equals(statusPagamento, pagamento.statusPagamento) && Objects.equals(ordemServico, pagamento.ordemServico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagamento, statusPagamento, ordemServico);
    }
}