package com.camila.livros.livrosapi.controller;

import com.camila.livros.livrosapi.dto.AssuntoRequestDTO;
import com.camila.livros.livrosapi.dto.AssuntoResponseDTO;
import com.camila.livros.livrosapi.service.AssuntoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/assuntos")
public class AssuntoController {

     private final AssuntoService assuntoService;

    public AssuntoController(AssuntoService assuntoService) {
        this.assuntoService = assuntoService;
    }


    @GetMapping
    public ResponseEntity<List<AssuntoResponseDTO>> listar() {
        return ResponseEntity.ok(assuntoService.listar());
    }


    @GetMapping("/{codAs}")
    public ResponseEntity<AssuntoResponseDTO> buscarAssuntoPorCodAs(@PathVariable Integer codAs) {
        return ResponseEntity.ok(assuntoService.buscarPorCodAs(codAs));
    }

    @PostMapping
    public ResponseEntity<AssuntoResponseDTO> incluir(@Valid @RequestBody AssuntoRequestDTO dto) {
        AssuntoResponseDTO criado = assuntoService.incluir(dto);

        URI location = URI.create("/assuntos/" + criado.codAs());

        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{codAs}")
    public ResponseEntity<AssuntoResponseDTO> atualizar(@PathVariable Integer codAs,
                                                      @Valid @RequestBody AssuntoRequestDTO dto) {
        return ResponseEntity.ok(assuntoService.atualizar(codAs, dto));
    }

    @DeleteMapping("/{codAs}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codAs) {
        assuntoService.deletar(codAs);
        return ResponseEntity.noContent().build();
    }
}
