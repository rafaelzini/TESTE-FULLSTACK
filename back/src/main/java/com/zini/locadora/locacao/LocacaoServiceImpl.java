package com.zini.locadora.locacao;

import com.zini.locadora.entity.Filme;
import com.zini.locadora.entity.Locacao;
import com.zini.locadora.entity.Usuario;
import com.zini.locadora.filme.FilmeService;
import com.zini.locadora.models.FilmeModel;
import com.zini.locadora.models.LocacaoModel;
import com.zini.locadora.models.RenovacaoLocacaoModel;
import com.zini.locadora.models.UsuarioModel;
import com.zini.locadora.usuario.UsuarioService;
import com.zini.locadora.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocacaoServiceImpl implements LocacaoService {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Override
    public LocacaoModel save(LocacaoModel model) throws Exception {

        if (model.getFilmes().size() > 5) {
            throw new Exception("Não é possivel alugar mais que 5 filmes!!");
        }

        for (FilmeModel f : model.getFilmes()) {
            if (!checaDisponibilidade(f.getId())) {
                throw new Exception("O Filme " + f.getNome() + " está Indisponivel");
            }
        }

        Locacao entity = new Locacao();
        modelToEntity(entity, model);
        entity.setDataParaDevolucao(entity.getDataLocacao().plusDays(5));
        entity = locacaoRepository.save(entity);
        return entityToModel(model, entity);
    }

    public Boolean checaDisponibilidade(Long filmeId) {
        FilmeModel filmeModel = filmeService.findById(filmeId);
        Long quantidadeAlugada = locacaoRepository.contaFilmesAlugadosPorId(filmeId);
        return filmeModel.getQuantidade() > quantidadeAlugada;
    }

    @Override
    public List<LocacaoModel> list() {
        List<LocacaoModel> results = new ArrayList<>();
        locacaoRepository.findAll().stream().map(r -> results.add(entityToModel(new LocacaoModel(), r))).collect(Collectors.toList());
        return results;
    }

    @Override
    public LocacaoModel findById(Long id) {
        Locacao entity = locacaoRepository.findById(id).get();
        return entityToModel(new LocacaoModel(), entity);
    }

    @Override
    public LocacaoModel devolucao(Long id) {
        Locacao entity = locacaoRepository.findById(id).get();
        entity.setDevolvido(true);
        entity.setDataDevolucao(Utils.nowTime());
        return entityToModel(new LocacaoModel(), entity);
    }


    @Override
    public LocacaoModel renovarLocacao(RenovacaoLocacaoModel model) throws Exception {
        Locacao entity = locacaoRepository.findById(model.getId()).get();

        if (entity.getRenovacoes() != null && entity.getRenovacoes() == 2) {
            throw new Exception("Maximo de renovações atingias!!");
        }
        if (entity.getRenovacoes() == null) {
            entity.setRenovacoes(1L);
        } else {
            entity.setRenovacoes(entity.getRenovacoes() + 1);
        }

        entity.setDataParaDevolucao(entity.getDataLocacao().plusDays(model.getDias()));
        locacaoRepository.save(entity);

        return entityToModel(new LocacaoModel(), entity);
    }

    @Override
    public void delete(Long id) {
        Locacao entity = locacaoRepository.findById(id).get();
        locacaoRepository.delete(entity);
    }

    private void modelToEntity(Locacao entity, LocacaoModel model) {
        entity.setDataLocacao(model.getDataLocacao());
        entity.setDataDevolucao(model.getDataDevolucao());

        entity.setFilmes(new ArrayList<>());
        model.getFilmes().stream().map(r -> entity.getFilmes().add(new Filme(r.getId()))).collect(Collectors.toList());

        entity.setUsuario(new Usuario(model.getUsuario().getId()));
        entity.setDataParaDevolucao(model.getDataParaDevolucao());
        entity.setId(model.getId());
    }

    private LocacaoModel entityToModel(LocacaoModel model, Locacao entity) {
        model.setDataLocacao(model.getDataLocacao());
        model.setDataDevolucao(model.getDataDevolucao());

        model.setFilmes(new ArrayList<>());
        entity.getFilmes().stream().map(r -> model.getFilmes().add(filmeService.entityToModel(new FilmeModel(), r))).collect(Collectors.toList());

        model.setUsuario(usuarioService.entityToModel(new UsuarioModel(), entity.getUsuario()));
        model.setDataParaDevolucao(model.getDataParaDevolucao());
        model.setId(entity.getId());
        return model;
    }

}
