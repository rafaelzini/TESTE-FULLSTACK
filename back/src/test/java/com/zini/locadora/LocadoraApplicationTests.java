package com.zini.locadora;

import com.zini.locadora.enums.GeneroEnum;
import com.zini.locadora.filme.FilmeService;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.UsuarioModel;
import com.zini.locadora.usuario.UsuarioRepository;
import com.zini.locadora.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class LocadoraApplicationTests {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    void insertFilme() {
        FilmeModel model = new FilmeModel();
        model.setDiretor("Joao");
        model.setGenero(GeneroEnum.TERROR);
        model.setQuantidade(5L);
        model.setNome("Filme de Terro");

        FilmeModel modelRetorno = filmeService.save(model);
        Assert.notNull(modelRetorno.getId(), "ID NAO CRIADO");

    }

    void insertUsuario() throws Exception {

        LocalDate date = LocalDate.parse("12/08/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        UsuarioModel model = new UsuarioModel();
        model.setDataNascimento(date);
        model.setNome("Rafael");
        model.setCpf("34663219802");

        UsuarioModel modelRetorno = usuarioService.save(model);
        Assert.notNull(modelRetorno.getId(), "ID NAO CRIADO");

    }

    @Test
    void contextLoads() {


    }

}
