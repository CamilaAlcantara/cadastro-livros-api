package com.camila.livros.livrosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssuntoRequestDTO(
        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 20, message = "A descrição deve ter no máximo 20 caracteres")
        String descricao
) {}
