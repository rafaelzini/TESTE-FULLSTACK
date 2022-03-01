package com.zini.locadora.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocacaoModel {

    private Long id;
    @NotNull
    private UsuarioModel usuario;
    @NotNull
    private List<FilmeModel> filmes;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataParaDevolucao;
    private LocalDateTime dataDevolucao;

}
