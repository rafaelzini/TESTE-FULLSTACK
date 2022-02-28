package com.zini.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


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
    @ManyToOne
    private Filme filme;
    @Column
    private LocalDateTime dataLocacao;
    @Column
    private LocalDateTime dataParaDevolucao;
    @Column
    private LocalDateTime dataDevolucao;
    @Column
    private Boolean devolvido;

}
