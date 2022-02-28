package com.zini.locadora.models;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocacaoModel {

    private Long id;
    @NotNull
    private UsuarioModel usuario;
    @NotNull
    private FilmeModel filme;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataParaDevolucao;
    private LocalDateTime dataDevolucao;

}
