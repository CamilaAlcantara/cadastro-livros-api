package com.camila.livros.livrosapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_au")
    public Integer codAu;

    @Column(name = "nome", nullable = false, length = 40)
    private String nome;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private List<Livro> livros;

}
