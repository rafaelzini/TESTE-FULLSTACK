package com.zini.locadora.filme;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.LocacaoModel;
import com.zini.locadora.utils.BaseFilter;
import com.zini.locadora.utils.TesteResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmeService {

    FilmeModel save(FilmeModel model);

    TesteResponse<FilmeModel> list(BaseFilter pageable);

    FilmeModel findById(Long id);

    void delete(Long id);

    void modelToEntity(Filme entity, FilmeModel model);

    FilmeModel entityToModel(FilmeModel model, Filme entity);
}
