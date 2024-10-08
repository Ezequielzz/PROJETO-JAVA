package com.br.ezequielzz.Controller;

import java.sql.SQLException;

import com.br.ezequielzz.Model.Turma;
import com.br.ezequielzz.Model.TurmaDAO;
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

    public void excluirTurma(int turmaId) throws SQLException {
        turmaDAO.excluirTurma(turmaId);
    }
}
