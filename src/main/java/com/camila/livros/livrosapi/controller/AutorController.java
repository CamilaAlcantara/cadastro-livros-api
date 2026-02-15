package com.camila.livros.livrosapi.controller;

import com.camila.livros.livrosapi.dto.AutorRequestDTO;
import com.camila.livros.livrosapi.dto.AutorResponseDTO;
import com.camila.livros.livrosapi.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {

     private final AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listar() {
        return ResponseEntity.ok(autorService.listar());
    }


    @GetMapping("/{codAu}")
    public ResponseEntity<AutorResponseDTO> buscarAutorPorCodAu(@PathVariable Integer codAu) {
        return ResponseEntity.ok(autorService.buscarPorCodAu(codAu));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> incluir(@Valid @RequestBody AutorRequestDTO dto) {
        AutorResponseDTO criado = autorService.incluir(dto);

        URI location = URI.create("/autores/" + criado.codAu());

        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{codAu}")
    public ResponseEntity<AutorResponseDTO> atualizar(@PathVariable Integer codAu,
                                                      @Valid @RequestBody AutorRequestDTO dto) {
        return ResponseEntity.ok(autorService.atualizar(codAu, dto));
    }

    @DeleteMapping("/{codAu}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codAu) {
        autorService.deletar(codAu);
        return ResponseEntity.noContent().build();
    }
}
