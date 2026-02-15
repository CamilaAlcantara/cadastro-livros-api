package com.camila.livros.livrosapi.service;

import com.camila.livros.livrosapi.dto.AssuntoRequestDTO;
import com.camila.livros.livrosapi.dto.AssuntoResponseDTO;
import com.camila.livros.livrosapi.entity.Assunto;
import com.camila.livros.livrosapi.exception.BusinessException;
import com.camila.livros.livrosapi.exception.ResourceNotFoundException;
import com.camila.livros.livrosapi.repository.AssuntoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssuntoService {

    private final AssuntoRepository assuntoRepository;


    public AssuntoService(AssuntoRepository assuntoRepository) {
        this.assuntoRepository = assuntoRepository;
    }

    @Transactional(readOnly = true)
    public List<AssuntoResponseDTO> listar() {
        return assuntoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AssuntoResponseDTO buscarPorCodAs(Integer codAs) {
        Assunto assunto = assuntoRepository.findById(codAs)
                .orElseThrow(() -> new ResourceNotFoundException("Assunto não encontrado: " + codAs));

        return toResponseDTO(assunto);
    }

    @Transactional
    public AssuntoResponseDTO incluir(AssuntoRequestDTO dto) {

        if (assuntoRepository.existsByDescricaoIgnoreCase(dto.descricao())) {
            throw new BusinessException("Já existe o assunto cadastrado.");
        }

        Assunto assunto = new Assunto();
        assunto.setDescricao(dto.descricao());

        Assunto salvo = assuntoRepository.save(assunto);
        return toResponseDTO(salvo);
    }

    @Transactional
    public AssuntoResponseDTO atualizar(Integer codAs, AssuntoRequestDTO dto) {
        Assunto assunto = assuntoRepository.findById(codAs)
                .orElseThrow(() -> new ResourceNotFoundException("Assunto não encontrado: " + codAs));

        if (assuntoRepository.existsByDescricaoIgnoreCase(dto.descricao())
                && !assunto.getDescricao().equalsIgnoreCase(dto.descricao())) {
            throw new BusinessException("Já existe o assunto cadastrado.");
        }

        assunto.setDescricao(dto.descricao());
        Assunto atualizado = assuntoRepository.save(assunto);

        return toResponseDTO(atualizado);
    }

    @Transactional
    public void deletar(Integer codAs) {
        if (!assuntoRepository.existsById(codAs)) {
            throw new ResourceNotFoundException("Assunto não encontrado: " + codAs);
        }
        assuntoRepository.deleteById(codAs);
    }

    private AssuntoResponseDTO toResponseDTO(Assunto assunto) {
        return new AssuntoResponseDTO(
                assunto.getCodAs(),
                assunto.getDescricao()
        );
    }
}
