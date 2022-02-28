package com.zini.locadora.locacao;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.entity.Locacao;
import com.zini.locadora.entity.Usuario;
import com.zini.locadora.filme.FilmeService;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.LocacaoModel;
import com.zini.locadora.models.UsuarioModel;
import com.zini.locadora.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocacaoServiceImpl implements LocacaoService {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Override
    public LocacaoModel save(LocacaoModel model) {
        Locacao entity = new Locacao();
        modelToEntity(entity, model);
        entity = locacaoRepository.save(entity);
        return entityToModel(model, entity);
    }

    @Override
    public List<LocacaoModel> list() {
        List<LocacaoModel> results = new ArrayList<>();
        locacaoRepository.findAll().stream().map(r -> results.add(entityToModel(new LocacaoModel(), r))).collect(Collectors.toList());
        return results;
    }

    @Override
    public LocacaoModel findById(Long id) {
        Locacao entity = locacaoRepository.findById(id).get();
        return entityToModel(new LocacaoModel(), entity);
    }

    @Override
    public void delete(Long id) {
        Locacao entity = locacaoRepository.findById(id).get();
        locacaoRepository.delete(entity);
    }

    private void modelToEntity(Locacao entity, LocacaoModel model) {
        entity.setDataLocacao(model.getDataLocacao());
        entity.setDataDevolucao(model.getDataDevolucao());
        entity.setFilme(new Filme(model.getFilme().getId()));
        entity.setUsuario(new Usuario(model.getUsuario().getId()));
        entity.setDataParaDevolucao(model.getDataParaDevolucao());
        entity.setId(model.getId());
    }

    private LocacaoModel entityToModel(LocacaoModel model, Locacao entity) {
        model.setDataLocacao(model.getDataLocacao());
        model.setDataDevolucao(model.getDataDevolucao());
        model.setFilme(filmeService.entityToModel(new FilmeModel(), entity.getFilme()));
        model.setUsuario(usuarioService.entityToModel(new UsuarioModel(), entity.getUsuario()));
        model.setDataParaDevolucao(model.getDataParaDevolucao());
        model.setId(entity.getId());
        return model;
    }

}
