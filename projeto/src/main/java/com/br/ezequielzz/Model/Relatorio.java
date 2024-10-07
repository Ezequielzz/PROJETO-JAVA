package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Relatorio {
    private int id;
    private String tipoRelatorio; // 'boletim', 'frequência', 'desempenho'
    private Date dataGeracao;
    private String dadosRelatorio;

    public Relatorio(int id, String tipoRelatorio, Date dataGeracao, String dadosRelatorio) {
        this.id = id;
        this.tipoRelatorio = tipoRelatorio;
        this.dataGeracao = dataGeracao;
        this.dadosRelatorio = dadosRelatorio;
    }

    // Método para gerar um boletim
    public String gerarBoletim(int alunoId) {
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        return relatorioDAO.gerarBoletim(alunoId);
    }

    // Método para gerar um relatório de frequência
    public String gerarRelatorioFrequencia(int alunoId) {
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        return relatorioDAO.gerarRelatorioFrequencia(alunoId);
    }
}
