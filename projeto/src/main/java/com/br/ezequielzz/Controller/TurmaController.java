package com.br.ezequielzz.Controller;

import java.sql.SQLException; // Importa a classe SQLException para manipulação de exceções SQL

import com.br.ezequielzz.Model.Turma; // Importa a classe Turma do modelo
import com.br.ezequielzz.Model.DAOs.TurmaDAO; // Importa a classe TurmaDAO para acessar dados de turmas
import com.br.ezequielzz.Model.Aluno; // Importa a classe Aluno do modelo

import java.util.List; // Importa a classe List para trabalhar com listas de objetos

// Controlador que gerencia operações relacionadas às turmas
public class TurmaController {
    private TurmaDAO turmaDAO; // Instância do DAO para turmas

    // Construtor do TurmaController
    public TurmaController() {
        this.turmaDAO = new TurmaDAO(); // Inicializa o DAO de turmas
    }

    // Método para criar uma nova turma
    public void criarTurma(Turma turma) {
        turmaDAO.criarTurma(turma); // Chama o método do DAO para criar a turma
    }

    // Método para adicionar um aluno a uma turma
    public void adicionarAluno(Turma turma, Aluno aluno) {
        turmaDAO.adicionarAlunoNaTurma(turma, aluno); // Chama o método do DAO para adicionar o aluno à turma
    }

    // Método para remover um aluno de uma turma
    public void removerAluno(Turma turma, Aluno aluno) {
        turmaDAO.removerAlunoDaTurma(turma, aluno); // Chama o método do DAO para remover o aluno da turma
    }

    // Método para listar todas as turmas
    public List<Turma> listarTodasTurmas() {
        return turmaDAO.listarTodasTurmas(); // Retorna a lista de todas as turmas
    }

    // Método para buscar uma turma por disciplina
    public Turma buscarPorDisciplina(int disciplinaId) {
        return turmaDAO.buscarPorDisciplina(disciplinaId); // Chama o método do DAO para buscar a turma pela disciplina
    }

    // Método para buscar a turma por ID do aluno
    public Turma buscarTurmaPorAluno(int alunoId) {
        try {
            return turmaDAO.buscarTurmaPorAluno(alunoId); // Chama o método no DAO para buscar a turma do aluno
        } catch (SQLException e) { // Captura exceções SQL
            // Aqui você pode tratar a exceção ou relatar um erro
            e.printStackTrace(); // Exibe a pilha de erro
            return null; // Retorna null em caso de erro
        }
    }

    // Método para excluir uma turma pelo ID
    public void excluirTurma(int turmaId) throws SQLException {
        turmaDAO.excluirTurma(turmaId); // Chama o método do DAO para excluir a turma
    }
} 
