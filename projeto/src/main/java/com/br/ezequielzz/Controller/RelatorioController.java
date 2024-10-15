package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.DAOs.RelatorioDAO; // Importa a classe RelatorioDAO para acessar dados de relatórios

import java.io.FileWriter; // Importa a classe FileWriter para escrita em arquivos
import java.io.IOException; // Importa a classe IOException para manipulação de exceções de entrada/saída

// Controlador que gerencia operações relacionadas aos relatórios
public class RelatorioController {

    private RelatorioDAO relatorioDAO; // Instância do DAO para relatórios

    // Construtor do RelatorioController
    public RelatorioController() {
        this.relatorioDAO = new RelatorioDAO(); // Inicializa o DAO de relatórios
    }

    // Método para gerar arquivo de relatório em formato txt para o boletim de um aluno
    public void gerarRelatorioBoletim(int alunoId, String caminhoArquivo) {
        String boletim = relatorioDAO.gerarBoletim(alunoId); // Chama o método do DAO para gerar o boletim

        // Tenta escrever o boletim em um arquivo
        try (FileWriter writer = new FileWriter(caminhoArquivo)) { // Usa try-with-resources para garantir que o writer será fechado
            writer.write(boletim); // Escreve o boletim no arquivo
            System.out.println("Boletim gerado no arquivo: " + caminhoArquivo); // Exibe mensagem de sucesso
        } catch (IOException e) { // Captura exceções de entrada/saída
            e.printStackTrace(); // Exibe a pilha de erro
        }
    }

    // Método para gerar arquivo de relatório de frequência em formato txt para um aluno
    public void gerarRelatorioFrequencia(int alunoId, String caminhoArquivo) {
        String relatorioFrequencia = relatorioDAO.gerarRelatorioFrequencia(alunoId); // Chama o método do DAO para gerar o relatório de frequência

        // Tenta escrever o relatório de frequência em um arquivo
        try (FileWriter writer = new FileWriter(caminhoArquivo)) { // Usa try-with-resources para garantir que o writer será fechado
            writer.write(relatorioFrequencia); // Escreve o relatório de frequência no arquivo
            System.out.println("Relatório de frequência gerado no arquivo: " + caminhoArquivo); // Exibe mensagem de sucesso
        } catch (IOException e) { // Captura exceções de entrada/saída
            e.printStackTrace(); // Exibe a pilha de erro
        }
    }
}
