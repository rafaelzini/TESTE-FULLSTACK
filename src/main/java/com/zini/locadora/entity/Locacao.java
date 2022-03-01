package com.zini.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Locacao {

    @Id
    @SequenceGenerator(name = "locacao_id_seq", sequenceName = "locacao_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locacao_id_seq")
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToMany
    @JoinTable(name = "locacao_filmes", joinColumns =
            {@JoinColumn(name = "locacao_id")}, inverseJoinColumns =
            {@JoinColumn(name = "filme_id")})
    private List<Filme> filmes;
    @Column
    private LocalDateTime dataLocacao;
    @Column
    private LocalDateTime dataParaDevolucao;
    @Column
    private LocalDateTime dataDevolucao;
    @Column
    private Boolean devolvido;
    @Column
    private Long renovacoes;

}
