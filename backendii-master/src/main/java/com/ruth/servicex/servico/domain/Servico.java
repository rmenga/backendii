package com.ruth.servicex.servico.domain;

import com.ruth.servicex.categoria.domain.Categoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "servicos"
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
    private Integer id;

    @Column(
            name = "nome"
    )
    private String nome;

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
