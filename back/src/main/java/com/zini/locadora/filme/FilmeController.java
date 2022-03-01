package com.zini.locadora.filme;


import com.zini.locadora.locacao.LocacaoService;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.LocacaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    public List<FilmeModel> findAll() {
        return service.list();
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
