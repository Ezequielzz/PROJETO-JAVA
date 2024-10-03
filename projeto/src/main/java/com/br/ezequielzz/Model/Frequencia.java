package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Frequencia {
    private int id;
    private int alunoId; // FK para Aluno
    private int disciplinaId; // FK para Disciplina
    private Date data;
    private boolean presenca;

    public Frequencia(int id, int alunoId, int disciplinaId, Date data, boolean presenca) {
        this.id = id;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.data = data;
        this.presenca = presenca;
    }

    // Método para registrar a frequência no banco de dados
    public void registrarFrequencia() {
        FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
        frequenciaDAO.registrarFrequencia(this);
    }

    // Método para atualizar a frequência no banco de dados
    public void atualizarFrequencia() {
        FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
        frequenciaDAO.atualizarFrequencia(this);
    }

    // Método para consultar a frequência de um aluno em uma disciplina
    public List<Frequencia> consultarFrequencia(int alunoId, int disciplinaId, Date dataInicial, Date dataFinal) {
        FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
        return frequenciaDAO.consultarFrequencia(alunoId, disciplinaId, dataInicial, dataFinal);
    }
}
