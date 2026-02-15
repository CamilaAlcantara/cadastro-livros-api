package com.camila.livros.livrosapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


}