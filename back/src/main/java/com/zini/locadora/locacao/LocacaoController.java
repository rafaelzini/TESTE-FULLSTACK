package com.zini.locadora.locacao;

import com.zini.locadora.models.LocacaoModel;
import com.zini.locadora.models.RenovacaoLocacaoModel;
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
    public LocacaoModel save(@Valid @RequestBody LocacaoModel model) throws Exception {
        return service.save(model);
    }

    @GetMapping("/{id}")
    public LocacaoModel findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/renovacao")
    public LocacaoModel renovarLocacao(@Valid @RequestBody RenovacaoLocacaoModel model) throws Exception {
        return service.renovarLocacao(model);
    }

    @PutMapping("/devolucao/{id}")
    public LocacaoModel devolucao(@PathVariable("id") Long id) throws Exception {
        return service.devolucao(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
