package com.camila.livros.livrosapi.repository;

import com.camila.livros.livrosapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    boolean existsByTituloIgnoreCase(String titulo);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}