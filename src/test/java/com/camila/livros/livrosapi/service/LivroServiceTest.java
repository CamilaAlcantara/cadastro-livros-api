package com.camila.livros.livrosapi.service;

import com.camila.livros.livrosapi.dto.LivroRequestDTO;
import com.camila.livros.livrosapi.entity.Assunto;
import com.camila.livros.livrosapi.entity.Autor;
import com.camila.livros.livrosapi.entity.Livro;
import com.camila.livros.livrosapi.exception.BusinessException;
import com.camila.livros.livrosapi.exception.ResourceNotFoundException;
import com.camila.livros.livrosapi.repository.AssuntoRepository;
import com.camila.livros.livrosapi.repository.AutorRepository;
import com.camila.livros.livrosapi.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {
    @Mock
    private LivroRepository livroRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AssuntoRepository assuntoRepository;

    @InjectMocks
    private LivroService livroService;

    private LivroRequestDTO dtoPadrao() {
        return new LivroRequestDTO(
                "Clean Code",
                "Prentice Hall",
                1,
                "2008",
                new BigDecimal("99.90"),
                List.of(10, 11),
                List.of(20, 21)
        );
    }


    @Test
    void incluir_deveMapearCorretamenteESalvar() {
        LivroRequestDTO dto = dtoPadrao();

        when(livroRepository.existsByTituloIgnoreCase(dto.titulo()))
                .thenReturn(false);

        Autor autor = new Autor();
        autor.setCodAu(10);
        autor.setNome("Robert C. Martin");

        Assunto assunto = new Assunto();
        assunto.setCodAs(20);
        assunto.setDescricao("Engenharia");

        when(autorRepository.findAllById(dto.autoresIds()))
                .thenReturn(List.of(autor));

        when(assuntoRepository.findAllById(dto.assuntosIds()))
                .thenReturn(List.of(assunto));

        when(livroRepository.save(any(Livro.class)))
                .thenAnswer(invocation -> {
                    Livro l = invocation.getArgument(0);
                    l.setCodL(1);
                    return l;
                });

        var response = livroService.incluir(dto);

        assertNotNull(response);
        assertEquals(1, response.codL());
        assertEquals(dto.valor(), response.valor());
        assertEquals(1, response.autores().size());
        assertEquals(1, response.assuntos().size());

        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);
        verify(livroRepository).save(captor.capture());

        Livro salvo = captor.getValue();
        assertEquals(dto.valor(), salvo.getValor());
        assertEquals("Clean Code", salvo.getTitulo());
    }
    @Test
    void incluir_deveLancarBusinessException_quandoTituloDuplicado() {
        LivroRequestDTO dto = dtoPadrao();

        when(livroRepository.existsByTituloIgnoreCase(dto.titulo()))
                .thenReturn(true);

        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> livroService.incluir(dto)
        );

        assertTrue(ex.getMessage().toLowerCase().contains("título"));
        verify(livroRepository, never()).save(any());
        verifyNoInteractions(autorRepository, assuntoRepository);
    }

    @Test
    void buscarPorCodL_deveLancarResourceNotFound_quandoNaoExiste() {
        when(livroRepository.findById(999)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> livroService.buscarPorCodL(999)
        );

        assertTrue(ex.getMessage().contains("999"));
    }

    @Test
    void deletar_deveLancarResourceNotFound_quandoNaoExiste() {
        when(livroRepository.existsById(123)).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> livroService.deletar(123)
        );

        assertTrue(ex.getMessage().contains("123"));
        verify(livroRepository, never()).deleteById(anyInt());
    }
}
