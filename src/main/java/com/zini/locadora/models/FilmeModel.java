package com.zini.locadora.models;

import com.zini.locadora.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeModel {

    private Long id;
    @NotEmpty
    private String nome;
    @NotNull
    private GeneroEnum genero;
    @NotEmpty
    private String diretor;
    @NotNull
    private Long quantidade;

}
