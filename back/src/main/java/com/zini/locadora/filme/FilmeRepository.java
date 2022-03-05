package com.zini.locadora.filme;

import com.zini.locadora.entity.Filme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Page<Filme> findAll(Pageable pageable);

}