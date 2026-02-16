package com.camila.livros.livrosapi.relatorio.repository;

import com.camila.livros.livrosapi.relatorio.dto.RelatorioLivroPorAutorDTO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RelatorioLivrosRepository {

    private final EntityManager em;

    public RelatorioLivrosRepository(EntityManager em) {
        this.em = em;
    }

    public List<RelatorioLivroPorAutorDTO> buscarLivrosPorAutor() {
        var sql = """
            SELECT
              cod_autor,
              nome_autor,
              cod_livro,
              titulo,
              editora,
              edicao,
              ano_publicacao,
              valor,
              assuntos
            FROM vw_relatorio_livros_por_autor
            ORDER BY nome_autor, titulo
            """;

        @SuppressWarnings("unchecked")
        List<Object[]> rows = em.createNativeQuery(sql).getResultList();

        return rows.stream().map(r -> new RelatorioLivroPorAutorDTO(
                (Integer) r[0],
                (String) r[1],
                (Integer) r[2],
                (String) r[3],
                (String) r[4],
                (Integer) r[5],
                (String) r[6],
                (BigDecimal) r[7],
                (String) r[8]
        )).toList();
    }
}