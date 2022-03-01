package com.zini.locadora.models;

import com.zini.locadora.enums.EnumSexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {

    private Long id;
    @NotEmpty
    private String nome;
    @NotNull
    private EnumSexo sexo;
    @NotEmpty
    private String cpf;
    @NotNull
    private LocalDate dataNascimento;

}
