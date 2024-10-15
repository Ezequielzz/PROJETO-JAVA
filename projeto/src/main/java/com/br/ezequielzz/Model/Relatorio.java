package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.br.ezequielzz.Model.DAOs.RelatorioDAO;

@Getter
@Setter
// Classe Relatorio que representa um relatório gerado para um aluno
public class Relatorio {
    // Atributo que armazena o ID único do relatório
    private int id;
    
    // Atributo que armazena o tipo de relatório, como 'boletim', 'frequência', ou 'desempenho'
    private String tipoRelatorio; // 'boletim', 'frequência', 'desempenho'
    
    // Atributo que armazena a data em que o relatório foi gerado
    private Date dataGeracao;
    
    // Atributo que armazena os dados do relatório em formato de string
    private String dadosRelatorio;

    // Construtor da classe Relatorio que inicializa os atributos do relatório
    public Relatorio(int id, String tipoRelatorio, Date dataGeracao, String dadosRelatorio) {
        this.id = id; // Define o ID do relatório
        this.tipoRelatorio = tipoRelatorio; // Define o tipo do relatório
        this.dataGeracao = dataGeracao; // Define a data de geração do relatório
        this.dadosRelatorio = dadosRelatorio; // Define os dados do relatório
    }

    // Método para gerar um boletim para um aluno específico
    public String gerarBoletim(int alunoId) {
        RelatorioDAO relatorioDAO = new RelatorioDAO(); // Cria uma instância de RelatorioDAO
        return relatorioDAO.gerarBoletim(alunoId); // Chama o método para gerar o boletim
    }

    // Método para gerar um relatório de frequência para um aluno específico
    public String gerarRelatorioFrequencia(int alunoId) {
        RelatorioDAO relatorioDAO = new RelatorioDAO(); // Cria uma instância de RelatorioDAO
        return relatorioDAO.gerarRelatorioFrequencia(alunoId); // Chama o método para gerar o relatório de frequência
    }
}
