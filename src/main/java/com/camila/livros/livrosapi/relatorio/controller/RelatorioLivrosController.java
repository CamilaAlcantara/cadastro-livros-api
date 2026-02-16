package com.camila.livros.livrosapi.relatorio.controller;

import com.camila.livros.livrosapi.relatorio.dto.RelatorioLivroPorAutorDTO;
import com.camila.livros.livrosapi.relatorio.service.RelatorioLivrosService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RelatorioLivrosController {

    private final RelatorioLivrosService service;

    public RelatorioLivrosController(RelatorioLivrosService service) {

        this.service = service;
    }

    @GetMapping("/relatorios/livros-por-autor/dados")
    public List<RelatorioLivroPorAutorDTO> dados() {
        return service.buscarLivrosPorAutor();
    }

    @GetMapping("/relatorios/livros-por-autor")
    public ResponseEntity<byte[]> pdf() {
        byte[] pdf = service.gerarPdfLivrosPorAutor();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-livros-por-autor.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}