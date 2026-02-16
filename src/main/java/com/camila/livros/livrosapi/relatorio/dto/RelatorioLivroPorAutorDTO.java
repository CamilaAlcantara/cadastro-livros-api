package com.camila.livros.livrosapi.relatorio.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RelatorioLivroPorAutorDTO {

    private Integer codAutor;
    private String nomeAutor;
    private Integer codLivro;
    private String titulo;
    private String editora;
    private Integer edicao;
    private String anoPublicacao;
    private BigDecimal valor;
    private String assuntos;

    public RelatorioLivroPorAutorDTO(Integer codAutor, String nomeAutor, Integer codLivro, String titulo,
                                     String editora, Integer edicao, String anoPublicacao,
                                     BigDecimal valor, String assuntos) {
        this.codAutor = codAutor;
        this.nomeAutor = nomeAutor;
        this.codLivro = codLivro;
        this.titulo = titulo;
        this.editora = editora;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.valor = valor;
        this.assuntos = assuntos;
    }

}