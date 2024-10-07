package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Relatorio;
import com.br.ezequielzz.Model.RelatorioDAO;

import java.io.BufferedWriter;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class RelatorioController {

    private RelatorioDAO relatorioDAO;

    public RelatorioController() {
        this.relatorioDAO = new RelatorioDAO();
    }

    // Método para gerar arquivo de relatório em formato txt
    public void gerarRelatorioBoletim(int alunoId, String caminhoArquivo) {
        String boletim = relatorioDAO.gerarBoletim(alunoId);

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write(boletim);
            System.out.println("Boletim gerado no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para gerar arquivo de relatório de frequência em formato txt
    public void gerarRelatorioFrequencia(int alunoId, String caminhoArquivo) {
        String relatorioFrequencia = relatorioDAO.gerarRelatorioFrequencia(alunoId);

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write(relatorioFrequencia);
            System.out.println("Relatório de frequência gerado no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
