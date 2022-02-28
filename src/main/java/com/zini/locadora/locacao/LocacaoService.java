package com.zini.locadora.locacao;

import com.zini.locadora.models.LocacaoModel;

import java.util.List;

public interface LocacaoService {

    LocacaoModel save(LocacaoModel model) throws Exception;

    List<LocacaoModel> list();

    LocacaoModel findById(Long id);

    void delete(Long id);

}
