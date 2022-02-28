package com.zini.locadora.filme;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.LocacaoModel;

import java.util.List;

public interface FilmeService {

    FilmeModel save(FilmeModel model);

    List<FilmeModel> list();

    FilmeModel findById(Long id);

    void delete(Long id);

    void modelToEntity(Filme entity, FilmeModel model);

    FilmeModel entityToModel(FilmeModel model, Filme entity);
}
