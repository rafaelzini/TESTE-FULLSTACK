package com.zini.locadora.locacao;

import com.zini.locadora.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    @Query(value = "select count(*) from locacao l where l.filme_id = :filmeId and devolvido is null or devolvido = false", nativeQuery = true)
    Long contaFilmesAlugadosPorId(@Param("filmeId") Long filmeId);

}