package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Relatorio;
import com.br.ezequielzz.Model.RelatorioDAO;

import java.util.Date;
import java.util.List;

public class RelatorioController {

    private RelatorioDAO relatorioDAO;

    public RelatorioController() {
        this.relatorioDAO = new RelatorioDAO();
    }

    public void gerarRelatorio(String tipoRelatorio, Date dataGeracao, String dadosRelatorio) {
        Relatorio relatorio = new Relatorio(0, tipoRelatorio, dataGeracao, dadosRelatorio);
        relatorioDAO.gerarRelatorio(relatorio);
    }

    public Relatorio buscarRelatorioPorId(int id) {
        return relatorioDAO.buscarRelatorioPorId(id);
    }

    public List<Relatorio> listarRelatoriosPorTipo(String tipoRelatorio) {
        return relatorioDAO.listarRelatoriosPorTipo(tipoRelatorio);
    }

    public String gerarBoletim(int alunoId) {
        return relatorioDAO.gerarBoletim(alunoId);
    }

    public String gerarRelatorioFrequencia(int alunoId) {
        return relatorioDAO.gerarRelatorioFrequencia(alunoId);
    }

    public String gerarRelatorioDesempenho(int turmaId) {
        return relatorioDAO.gerarRelatorioDesempenho(turmaId);
    }
}
