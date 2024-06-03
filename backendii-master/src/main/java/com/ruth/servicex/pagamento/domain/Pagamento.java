package com.ruth.servicex.pagamento.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ruth.servicex.ordemServico.domain.OrdemServico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "pagamento"
)
@Getter
@Setter
@EqualsAndHashCode(
        onlyExplicitlyIncluded = true
)
@Inheritance(
        strategy = InheritanceType.JOINED
)
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PagamentoBoleto.class, name = "pagamentoBoleto")
})
public abstract class Pagamento {

    @Id
    @EqualsAndHashCode.Include
    private Integer idPagamento;

    @Column(name = "STATUS_PAGAMENTO")
    private Integer statusPagamento = StatusPagamento.PENDENTE.getCod();

    @OneToOne
    @MapsId
    @JoinColumn(name = "ordem_servico_id")
    @JsonIgnore
    private OrdemServico ordemServico;

    public Pagamento(Integer id, StatusPagamento statusPagamento, OrdemServico ordemServico) {
        this.idPagamento = id;
        this.statusPagamento = (statusPagamento == null) ? null : statusPagamento.getCod();
        this.ordemServico = ordemServico;
    }

    public StatusPagamento getStatusPagamento() {
        return StatusPagamento.toEnum(statusPagamento);
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento.getCod();
    }
}
