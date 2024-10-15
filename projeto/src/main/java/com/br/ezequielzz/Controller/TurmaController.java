package com.br.ezequielzz.Controller;

import java.sql.SQLException;

import com.br.ezequielzz.Model.Turma;
import com.br.ezequielzz.Model.DAOs.TurmaDAO;
import com.br.ezequielzz.Model.Aluno;

import java.util.List;

public class TurmaController {
    private TurmaDAO turmaDAO;

    public TurmaController() {
        this.turmaDAO = new TurmaDAO();
    }

    // Criar uma nova turma
    public void criarTurma(Turma turma) {
        turmaDAO.criarTurma(turma);
    }

    // Adicionar aluno a uma turma
    public void adicionarAluno(Turma turma, Aluno aluno) {
        turmaDAO.adicionarAlunoNaTurma(turma, aluno);
    }

    // Remover aluno de uma turma
    public void removerAluno(Turma turma, Aluno aluno) {
        turmaDAO.removerAlunoDaTurma(turma, aluno);
    }

    // Listar todas as turmas
    public List<Turma> listarTodasTurmas() {
        return turmaDAO.listarTodasTurmas();
    }

    // Buscar uma turma por disciplina
    public Turma buscarPorDisciplina(int disciplinaId) {
        return turmaDAO.buscarPorDisciplina(disciplinaId);
    }

    // Método para buscar a turma por ID do aluno
    public Turma buscarTurmaPorAluno(int alunoId) {
        try {
            return turmaDAO.buscarTurmaPorAluno(alunoId); // Chama o método no DAO
        } catch (SQLException e) {
            // Aqui você pode tratar a exceção ou relatar um erro
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }


    public void excluirTurma(int turmaId) throws SQLException {
        turmaDAO.excluirTurma(turmaId);
    }
}
