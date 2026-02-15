package com.camila.livros.livrosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 40, message = "O deve ter no máximo 40 caracteres")
        String nome
) {}
