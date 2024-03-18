package com.ruth.servicex.categoria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruth.servicex.servico.domain.Servico;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@Table(
        name= "CATEGORIA"
)
@EqualsAndHashCode(
        onlyExplicitlyIncluded = true
)
public class Categoria {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column (name = "ID_CATEGORIA")
    @EqualsAndHashCode.Include
    private Integer idCategoria;

    @Column(name = "NOME_CATEGORIA")
    private String nomeCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Servico> Servicos;
}
