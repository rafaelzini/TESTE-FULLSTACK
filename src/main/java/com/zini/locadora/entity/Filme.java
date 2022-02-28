package com.zini.locadora.entity;

import com.zini.locadora.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Filme {

    @Id
    @SequenceGenerator(name = "filme_id_seq", sequenceName = "filme_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filme_id_seq")
    private Long id;
    @Column
    private String nome;
    @Column
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;
    @Column
    private String diretor;
    @Column
    private Long quantidade;

    public Filme(Long id) {
        this.id = id;
    }
}
