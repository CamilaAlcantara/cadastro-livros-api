package com.camila.livros.livrosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record LivroRequestDTO(
        @NotBlank String titulo,
        @NotBlank String editora,
        @NotNull Integer edicao,
        @NotBlank @Size(max = 4) String anoPublicacao,
        @NotNull BigDecimal valor,
        List<Integer> autoresIds,
        List<Integer> assuntosIds
) {}