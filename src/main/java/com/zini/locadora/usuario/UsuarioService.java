package com.zini.locadora.usuario;

import com.zini.locadora.entity.Usuario;
import com.zini.locadora.models.UsuarioModel;

import java.util.List;

public interface UsuarioService {

    UsuarioModel save(UsuarioModel model) throws Exception;

    List<UsuarioModel> list();

    UsuarioModel findById(Long id);

    void delete(Long id);

    void modelToEntity(Usuario entity, UsuarioModel model);

    UsuarioModel entityToModel(UsuarioModel model, Usuario entity);
}
