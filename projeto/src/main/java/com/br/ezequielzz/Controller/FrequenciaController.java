package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Frequencia;
import com.br.ezequielzz.Model.FrequenciaDAO;

import java.util.Date;
import java.util.List;

public class FrequenciaController {
    private FrequenciaDAO frequenciaDAO;

    public FrequenciaController() {
        this.frequenciaDAO = new FrequenciaDAO();
    }

    // Registrar uma nova frequência
    public void registrarFrequencia(Frequencia frequencia) {
        frequenciaDAO.registrarFrequencia(frequencia);
    }

    // Atualizar uma frequência existente
    public void atualizarFrequencia(Frequencia frequencia) {
        frequenciaDAO.atualizarFrequencia(frequencia);
    }

    // Buscar frequência por ID
    public Frequencia buscarFrequenciaPorId(int id) {
        return frequenciaDAO.buscarFrequenciaPorId(id);
    }

    // Consultar frequência de um aluno em uma disciplina em determinado período
    public List<Frequencia> consultarFrequencia(int alunoId, int disciplinaId, Date dataInicial, Date dataFinal) {
        return frequenciaDAO.consultarFrequencia(alunoId, disciplinaId, dataInicial, dataFinal);
    }
}
