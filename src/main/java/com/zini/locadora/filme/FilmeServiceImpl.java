package com.zini.locadora.filme;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.models.FilmeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeServiceImpl implements FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public FilmeModel save(FilmeModel model) {
        Filme entity = new Filme();
        modelToEntity(entity, model);
        entity = filmeRepository.save(entity);
        return entityToModel(model, entity);
    }

    @Override
    public List<FilmeModel> list() {
        List<FilmeModel> results = new ArrayList<>();
        filmeRepository.findAll().stream().map(r -> results.add(entityToModel(new FilmeModel(), r))).collect(Collectors.toList());
        return results;
    }

    @Override
    public FilmeModel findById(Long id) {
        Filme entity = filmeRepository.findById(id).get();
        return entityToModel(new FilmeModel(), entity);
    }

    @Override
    public void delete(Long id) {
        Filme entity = filmeRepository.findById(id).get();
        filmeRepository.delete(entity);
    }

    @Override
    public void modelToEntity(Filme entity, FilmeModel model) {
        entity.setDiretor(model.getDiretor());
        entity.setGenero(model.getGenero());
        entity.setNome(model.getNome());
        entity.setQuantidade(model.getQuantidade());
        entity.setId(model.getId());
    }

    @Override
    public FilmeModel entityToModel(FilmeModel model, Filme entity) {
        model.setId(entity.getId());
        model.setDiretor(entity.getDiretor());
        model.setGenero(entity.getGenero());
        model.setNome(entity.getNome());
        model.setQuantidade(entity.getQuantidade());
        return model;
    }

}
