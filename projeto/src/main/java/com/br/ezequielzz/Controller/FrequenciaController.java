package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Frequencia; // Importa a classe Frequencia do modelo
import com.br.ezequielzz.Model.DAOs.FrequenciaDAO; // Importa a classe FrequenciaDAO para acessar dados de frequência

import java.util.Date; // Importa a classe Date para trabalhar com datas
import java.util.List; // Importa a classe List para trabalhar com listas de objetos

// Controlador que gerencia operações relacionadas à frequência
public class FrequenciaController {
    private FrequenciaDAO frequenciaDAO; // Instância do DAO para frequência

    // Construtor do FrequenciaController
    public FrequenciaController() {
        this.frequenciaDAO = new FrequenciaDAO(); // Inicializa o DAO de frequência
    }

    // Método para registrar uma nova frequência
    public void registrarFrequencia(Frequencia frequencia) {
        frequenciaDAO.registrarFrequencia(frequencia); // Chama o método do DAO para registrar a frequência
    }

    // Método para atualizar uma frequência existente
    public void atualizarFrequencia(Frequencia frequencia) {
        frequenciaDAO.atualizarFrequencia(frequencia); // Chama o método do DAO para atualizar a frequência
    }

    // Método para buscar frequência por ID
    public Frequencia buscarFrequenciaPorId(int id) {
        return frequenciaDAO.buscarFrequenciaPorId(id); // Retorna a frequência correspondente ao ID fornecido
    }

    // Método para consultar frequência de um aluno em uma disciplina em determinado período
    public List<Frequencia> consultarFrequencia(int alunoId, int disciplinaId, Date dataInicial, Date dataFinal) {
        return frequenciaDAO.consultarFrequencia(alunoId, disciplinaId, dataInicial, dataFinal); // Retorna a lista de frequências do aluno na disciplina entre as datas especificadas
    }
}
