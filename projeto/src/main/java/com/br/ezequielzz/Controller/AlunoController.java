package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.DAOs.AlunoDAO;
import com.br.ezequielzz.Model.DAOs.MatriculaDAO;

import java.sql.SQLException;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;
    private MatriculaDAO matriculaDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        this.matriculaDAO = new MatriculaDAO();
    }

    public boolean criarAluno(Aluno aluno) {
        try {
            alunoDAO.criarAluno(aluno);
            return true; // Sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falha
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

    // Desligar o aluno (atualiza o status para "desligado")
    public void desligarAluno(int alunoId) throws SQLException {
        Aluno aluno = alunoDAO.buscarAlunoPorId(alunoId);
        if (aluno != null) {
            aluno.setStatusMatricula("desligado");
            alunoDAO.atualizarAluno(aluno);

            // Cancelar a matrícula do aluno
            matriculaDAO.cancelarMatricula(alunoId);
        } else {
            throw new SQLException("Aluno não encontrado para desligar.");
        }
    }   

    // Transferir o aluno para outra turma
    public void transferirAluno(int alunoId, int novaTurmaId) throws SQLException {
        Aluno aluno = alunoDAO.buscarAlunoPorId(alunoId);
        if (aluno != null) {
            aluno.setTurmaId(novaTurmaId);
            alunoDAO.atualizarAluno(aluno);
        } else {
            throw new SQLException("Aluno não encontrado para transferir.");
        }
    }
}
