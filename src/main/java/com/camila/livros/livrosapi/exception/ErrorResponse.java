package com.camila.livros.livrosapi.exception;

import java.time.OffsetDateTime;

public record ErrorResponse(
        Integer status,
        String error,
        String message,
        String path,
        OffsetDateTime timestamp
){}