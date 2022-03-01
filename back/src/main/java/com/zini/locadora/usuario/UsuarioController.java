package com.zini.locadora.usuario;


import com.zini.locadora.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioModel> findAll() {
        return service.list();
    }

    @PostMapping
    public UsuarioModel save(@Valid @RequestBody UsuarioModel dto) throws Exception {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public UsuarioModel findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }


}
