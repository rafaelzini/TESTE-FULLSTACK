package com.zini.locadora.locacao;

import com.zini.locadora.models.LocacaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    @Autowired
    private LocacaoService service;

    @GetMapping
    public List<LocacaoModel> findAll() {
        return service.list();
    }

    @PostMapping
    public LocacaoModel save(@Valid @RequestBody LocacaoModel dto) {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public LocacaoModel findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
