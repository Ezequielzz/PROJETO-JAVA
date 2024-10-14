package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.DAOs.AlunoDAO;

import java.sql.SQLException;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    public boolean criarAluno(Aluno aluno) {
        try {
            alunoDAO.criarAluno(aluno);
            return true;  // Sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Falha
        }
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos();
    }

    // Listar alunos por turma
    public List<Aluno> listarAlunosPorTurma(int turmaId) {
        return alunoDAO.listarAlunosPorTurma(turmaId);
    }

    public void excluirAluno(int alunoId) throws SQLException {
        alunoDAO.excluirAluno(alunoId);
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        alunoDAO.atualizarAluno(aluno);
    }

    public Aluno buscarAlunoPorId(int id) throws SQLException {
        return alunoDAO.buscarAlunoPorId(id);
    }
}
