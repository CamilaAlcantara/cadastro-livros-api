package com.camila.livros.livrosapi.service;

import com.camila.livros.livrosapi.dto.AutorRequestDTO;
import com.camila.livros.livrosapi.dto.AutorResponseDTO;
import com.camila.livros.livrosapi.entity.Autor;
import com.camila.livros.livrosapi.exception.BusinessException;
import com.camila.livros.livrosapi.exception.ResourceNotFoundException;
import com.camila.livros.livrosapi.repository.AutorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {


   private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional(readOnly = true)
    public List<AutorResponseDTO> listar() {
        return autorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    public AutorResponseDTO buscarPorCodAu(Integer codAu) {
        Autor autor = autorRepository.findById(codAu)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado: " + codAu));

        return toResponseDTO(autor);
    }

    @Transactional
    public AutorResponseDTO inserir(AutorRequestDTO dto) {

        if (autorRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new BusinessException("Já existe autor cadastrado com esse nome.");
        }

        Autor autor = new Autor();
        autor.setNome(dto.nome());

        Autor salvo = autorRepository.save(autor);
        return toResponseDTO(salvo);
    }

    @Transactional
    public AutorResponseDTO atualizar(Integer codAu, AutorRequestDTO dto) {
        Autor autor = autorRepository.findById(codAu)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado: " + codAu));

        if (autorRepository.existsByNomeIgnoreCase(dto.nome())
                && !autor.getNome().equalsIgnoreCase(dto.nome())) {
            throw new BusinessException("Já existe autor cadastrado com esse nome.");
        }

        autor.setNome(dto.nome());
        Autor atualizado = autorRepository.save(autor);

        return toResponseDTO(atualizado);
    }
    @Transactional
    public void deletar(Integer codAu) {
        if (!autorRepository.existsById(codAu)) {
            throw new ResourceNotFoundException("Autor não encontrado: " + codAu);
        }
        autorRepository.deleteById(codAu);
    }

    private AutorResponseDTO toResponseDTO(Autor autor) {
        return new AutorResponseDTO(
                autor.getCodAu(),
                autor.getNome()
        );
    }

}
