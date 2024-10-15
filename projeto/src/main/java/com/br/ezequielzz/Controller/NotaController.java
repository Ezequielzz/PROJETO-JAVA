package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Nota; // Importa a classe Nota do modelo
import com.br.ezequielzz.Model.DAOs.NotaDAO; // Importa a classe NotaDAO para acessar dados de notas

// Controlador que gerencia operações relacionadas às notas
public class NotaController {
    private NotaDAO notaDAO; // Instância do DAO para notas

    // Construtor do NotaController
    public NotaController() {
        this.notaDAO = new NotaDAO(); // Inicializa o DAO de notas
    }

    // Método para registrar uma nova nota para um aluno em uma disciplina
    public void registrarNota(int alunoId, int disciplinaId, float valorNota, java.util.Date data) {
        Nota nota = new Nota(0, alunoId, disciplinaId, valorNota, data); // Cria uma nova instância de Nota
        notaDAO.registrarNota(nota); // Chama o método do DAO para registrar a nota
    }

    // Método para atualizar uma nota existente
    public void atualizarNota(int id, float novoValorNota, java.util.Date novaData) {
        Nota nota = notaDAO.buscarNotaPorId(id); // Busca a nota pelo ID
        if (nota != null) { // Verifica se a nota foi encontrada
            nota.setValorNota(novoValorNota); // Atualiza o valor da nota
            nota.setData(novaData); // Atualiza a data da nota
            notaDAO.atualizarNota(nota); // Chama o método do DAO para atualizar a nota
        } else {
            System.out.println("Nota não encontrada."); // Exibe mensagem se a nota não foi encontrada
        }
    }

    // Método para buscar uma nota pelo ID
    public Nota buscarNotaPorId(int id) {
        return notaDAO.buscarNotaPorId(id); // Retorna a nota correspondente ao ID fornecido
    }
} 
