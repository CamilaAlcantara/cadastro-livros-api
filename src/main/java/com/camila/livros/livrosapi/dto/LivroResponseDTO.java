package com.camila.livros.livrosapi.dto;

import java.math.BigDecimal;
import java.util.List;

public record LivroResponseDTO(
    Integer codL,
    String titulo,
    String editora,
    Integer edicao,
    String anoPublicacao,
    BigDecimal valor,
    List<AutorResponseDTO> autores,
    List<AssuntoResponseDTO> assuntos
) {}