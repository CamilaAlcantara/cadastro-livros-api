package com.camila.livros.livrosapi.repository;

import com.camila.livros.livrosapi.entity.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository  extends JpaRepository<Assunto, Integer> {
    boolean existsByDescricaoIgnoreCase(String descricao);
}
