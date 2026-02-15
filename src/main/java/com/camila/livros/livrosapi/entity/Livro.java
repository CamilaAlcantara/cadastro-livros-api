package com.camila.livros.livrosapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_l")
    private Integer codL;

    private String titulo;
    private String editora;
    private Integer edicao;

    @Column(name = "ano_publicacao")
    private String anoPublicacao;

    private BigDecimal valor;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_cod_l"),
            inverseJoinColumns = @JoinColumn(name = "autor_cod_au")
    )
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(
            name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_cod_l"),
            inverseJoinColumns = @JoinColumn(name = "assunto_cod_as")
    )
    private List<Assunto> assuntos;
}