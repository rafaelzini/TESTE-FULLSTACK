package com.zini.locadora.locacao;

import com.zini.locadora.models.LocacaoModel;
import com.zini.locadora.models.RenovacaoLocacaoModel;

import java.util.List;

public interface LocacaoService {

    LocacaoModel save(LocacaoModel model) throws Exception;

    List<LocacaoModel> list();

    LocacaoModel findById(Long id);

    LocacaoModel devolucao(Long id);

    LocacaoModel renovarLocacao(RenovacaoLocacaoModel model) throws Exception;

    void delete(Long id);

}
