package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.AlunoDAO;

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

    public List<String> consultarHistorico(int alunoId) {
        // Chama o DAO para consultar o histórico no banco
        return alunoDAO.consultarHistorico(alunoId);
    }


}
