package com.zini.locadora.usuario;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.entity.Usuario;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.UsuarioModel;
import com.zini.locadora.utils.Utils;
import com.zini.locadora.utils.UtilsCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioModel save(UsuarioModel model) throws Exception {

        if (!UtilsCpfCnpj.isCPF(model.getCpf())) {
            throw new Exception("CPF INVALIDO!!");
        }

        Period period = model.getDataNascimento().until(Utils.now());
        if (period.getYears() < 18) {
            throw new Exception("Não é permitudo cadastro de menos de 18 anos!!");
        }

        Usuario entity = new Usuario();
        modelToEntity(entity, model);
        entity = usuarioRepository.save(entity);
        return entityToModel(model, entity);
    }

    @Override
    public List<UsuarioModel> list() {
        List<UsuarioModel> results = new ArrayList<>();
        usuarioRepository.findAll().stream().map(r -> results.add(entityToModel(new UsuarioModel(), r))).collect(Collectors.toList());
        return results;
    }

    @Override
    public UsuarioModel findById(Long id) {
        Usuario entity = usuarioRepository.findById(id).get();
        return entityToModel(new UsuarioModel(), entity);
    }

    @Override
    public void delete(Long id) {
        Usuario entity = usuarioRepository.findById(id).get();
        usuarioRepository.delete(entity);
    }

    @Override
    public void modelToEntity(Usuario entity, UsuarioModel model) {
        entity.setCpf(model.getCpf());
        entity.setSexo(model.getSexo());
        entity.setNome(model.getNome());
        entity.setId(model.getId());
        entity.setDataNascimento(model.getDataNascimento());
    }

    @Override
    public UsuarioModel entityToModel(UsuarioModel model, Usuario entity) {
        model.setCpf(entity.getCpf());
        model.setSexo(entity.getSexo());
        model.setNome(entity.getNome());
        model.setId(entity.getId());
        model.setDataNascimento(entity.getDataNascimento());
        return model;
    }

}
