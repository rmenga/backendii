package com.ruth.servicex.servico.domain;

import com.ruth.servicex.categoria.domain.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "SERVICO"
)
@Data
@EqualsAndHashCode(
        onlyExplicitlyIncluded = true
)
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @EqualsAndHashCode.Include
    private Integer idServico;

    @Column(
            name = "nome"
    )
    private String nomeServico;

    @Column(
            name = "valor"
    )
    private Double valor;

    @ManyToOne
    @JoinColumn(
            name = "categoria_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_servico_categoria_categoria_id"
            )
    )
    private Categoria categoria;
}
