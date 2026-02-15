package com.camila.livros.livrosapi.controller;

import com.camila.livros.livrosapi.dto.LivroRequestDTO;
import com.camila.livros.livrosapi.dto.LivroResponseDTO;
import com.camila.livros.livrosapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {

        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listar() {
        return ResponseEntity.ok(livroService.listar());
    }

    @GetMapping("/{codL}")
    public ResponseEntity<LivroResponseDTO> buscarLivroPorCodL(@PathVariable Integer codL) {
        return ResponseEntity.ok(livroService.buscarPorCodL(codL));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> incluir(@Valid @RequestBody LivroRequestDTO dto) {
        LivroResponseDTO criado = livroService.incluir(dto);

        URI location = URI.create("/livros/" + criado.codL());

        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{codL}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Integer codL,
                                                      @Valid @RequestBody LivroRequestDTO dto) {
        return ResponseEntity.ok(livroService.atualizar(codL, dto));
    }

    @DeleteMapping("/{codL}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codL) {
        livroService.deletar(codL);
        return ResponseEntity.noContent().build();
    }
}