package com.zini.locadora.filme;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.utils.BaseFilter;
import com.zini.locadora.utils.TesteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public TesteResponse<FilmeModel> list(BaseFilter baseFilter) {

        Pageable pagination = PageRequest.of(0, (int) filmeRepository.count());
        if (baseFilter.getPage() != null && baseFilter.getSize() != null) {
            pagination = PageRequest.of(baseFilter.getPage().intValue(), baseFilter.getSize().intValue());
        }

        List<FilmeModel> results = new ArrayList<>();
        Page<Filme> page = filmeRepository.findAll(pagination);
        page.getContent().stream().map(r -> results.add(entityToModel(new FilmeModel(), r))).collect(Collectors.toList());

        TesteResponse<FilmeModel> result = new TesteResponse<>();
        result.setFirst(page.isFirst());
        result.setPageNumber(page.getNumber());
        result.setTotalPages(page.getTotalPages());
        result.setPageSize(page.getSize());
        result.setTotalElements(page.getTotalElements());
        result.setNumberOfElements(page.getNumberOfElements());
        result.setLast(page.isLast());
        result.setContent(results);

        return result;
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

