package com.camila.livros.livrosapi.relatorio.service;

import com.camila.livros.livrosapi.relatorio.dto.RelatorioLivroPorAutorDTO;
import com.camila.livros.livrosapi.relatorio.repository.RelatorioLivrosRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioLivrosService {

    private final RelatorioLivrosRepository repository;

    public RelatorioLivrosService(RelatorioLivrosRepository repository) {
        this.repository = repository;
    }

    public List<RelatorioLivroPorAutorDTO> buscarLivrosPorAutor() {
        return repository.buscarLivrosPorAutor();
    }

    public byte[] gerarPdfLivrosPorAutor() {
        List<RelatorioLivroPorAutorDTO> dados = repository.buscarLivrosPorAutor();

        try (InputStream jrxml = new ClassPathResource("reports/livros-por-autor.jrxml").getInputStream()) {

            JasperReport report = JasperCompileManager.compileReport(jrxml);

            Map<String, Object> params = new HashMap<>();

            params.put(net.sf.jasperreports.engine.JRParameter.REPORT_LOCALE, new java.util.Locale("pt", "BR"));

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

            JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);

            return JasperExportManager.exportReportToPdf(print);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório PDF", e);
        }
    }
}