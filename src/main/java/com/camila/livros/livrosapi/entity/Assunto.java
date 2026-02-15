package com.camila.livros.livrosapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "assunto")
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_as")
    public Integer codAs;

    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    @JsonIgnore
    private List<Livro> livros;


}