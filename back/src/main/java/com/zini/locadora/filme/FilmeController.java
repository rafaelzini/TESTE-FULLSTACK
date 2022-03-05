package com.zini.locadora.filme;


import com.zini.locadora.locacao.LocacaoService;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.LocacaoModel;
import com.zini.locadora.utils.BaseFilter;
import com.zini.locadora.utils.TesteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    public TesteResponse<FilmeModel> findAll(BaseFilter baseFilter) {
        return service.list(baseFilter);
    }

    @PostMapping
    public FilmeModel save(@Valid @RequestBody FilmeModel dto) {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public FilmeModel findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
