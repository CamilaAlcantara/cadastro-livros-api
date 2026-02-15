package com.camila.livros.livrosapi.service;

import com.camila.livros.livrosapi.dto.AssuntoResponseDTO;
import com.camila.livros.livrosapi.dto.AutorResponseDTO;
import com.camila.livros.livrosapi.dto.LivroRequestDTO;
import com.camila.livros.livrosapi.dto.LivroResponseDTO;
import com.camila.livros.livrosapi.entity.Assunto;
import com.camila.livros.livrosapi.entity.Autor;
import com.camila.livros.livrosapi.entity.Livro;
import com.camila.livros.livrosapi.exception.BusinessException;
import com.camila.livros.livrosapi.exception.ResourceNotFoundException;
import com.camila.livros.livrosapi.repository.AssuntoRepository;
import com.camila.livros.livrosapi.repository.AutorRepository;
import com.camila.livros.livrosapi.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final AssuntoRepository assuntoRepository;

    public LivroService(LivroRepository livroRepository, 
                        AutorRepository autorRepository, 
                        AssuntoRepository assuntoRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.assuntoRepository = assuntoRepository;
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> listar() {
        return livroRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LivroResponseDTO buscarPorCodL(Integer codL) {
        Livro livro = livroRepository.findById(codL)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado: " + codL));
        return toResponseDTO(livro);
    }

    @Transactional
    public LivroResponseDTO incluir(LivroRequestDTO dto) {
        if (livroRepository.existsByTituloIgnoreCase(dto.titulo())) {
            throw new BusinessException("Já existe um livro cadastrado com este título.");
        }

        Livro livro = new Livro();
        atualizarDadosEntidade(livro, dto);

        Livro salvo = livroRepository.save(livro);
        return toResponseDTO(salvo);
    }

    @Transactional
    public LivroResponseDTO atualizar(Integer codL, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(codL)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado: " + codL));

        if (livroRepository.existsByTituloIgnoreCase(dto.titulo()) 
                && !livro.getTitulo().equalsIgnoreCase(dto.titulo())) {
            throw new BusinessException("Já existe outro livro cadastrado com esse título.");
        }

        atualizarDadosEntidade(livro, dto);
        Livro atualizado = livroRepository.save(livro);
        return toResponseDTO(atualizado);
    }

    @Transactional
    public void deletar(Integer codL) {
        if (!livroRepository.existsById(codL)) {
            throw new ResourceNotFoundException("Livro não encontrado: " + codL);
        }
        livroRepository.deleteById(codL);
    }

    private void atualizarDadosEntidade(Livro livro, LivroRequestDTO dto) {
        livro.setTitulo(dto.titulo());
        livro.setEditora(dto.editora());
        livro.setEdicao(dto.edicao());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setValor(dto.valor());


        List<Autor> autores = autorRepository.findAllById(dto.autoresIds());
        livro.setAutores(autores);

        List<Assunto> assuntos = assuntoRepository.findAllById(dto.assuntosIds());
        livro.setAssuntos(assuntos);
    }

    // Convertendo a Entidade (com listas) para o ResponseDTO
    private LivroResponseDTO toResponseDTO(Livro livro) {
        List<AutorResponseDTO> autoresDTO = livro.getAutores().stream()
                .map(a -> new AutorResponseDTO(a.getCodAu(), a.getNome()))
                .toList();

        List<AssuntoResponseDTO> assuntosDTO = livro.getAssuntos().stream()
                .map(as -> new AssuntoResponseDTO(as.getCodAs(), as.getDescricao()))
                .toList();

        return new LivroResponseDTO(
                livro.getCodL(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getValor(),
                autoresDTO,
                assuntosDTO
        );
    }
}