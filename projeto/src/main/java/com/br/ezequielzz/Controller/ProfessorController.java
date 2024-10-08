package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfessorController {
    private Professor professor;
    private ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    public boolean criarProfessor(Professor professor) {
        try {
            professorDAO.criarProfessor(professor);
            return true;  // Sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Falha
        }
    }

    public List<Professor> listarTodosProfessores() {
        return professorDAO.listarTodos();
    }

    // Método para atribuir uma disciplina ao Professor
    public void atribuirDisciplina(Professor professor, Disciplina disciplina) {
        if (!professor.getDisciplinas().contains(disciplina)) {
            professor.getDisciplinas().add(disciplina);
            // Chamando a DAO para persistir a atribuição
            professorDAO.atribuirDisciplina(professor, disciplina);
            System.out.println("Disciplina atribuída ao professor com sucesso.");
        } else {
            System.out.println("Disciplina já atribuída ao professor.");
        }
    }

    // Método para lançar nota de um aluno em uma disciplina específica
    public void lancarNota(Professor professor, Aluno aluno, Disciplina disciplina, float valorNota) {
        if (professor.getDisciplinas().contains(disciplina)) {
            Nota nota = new Nota(0, aluno.getId(), disciplina.getId(), valorNota, new Date());
            // Aqui chamamos o DAO para persistir a nota no banco
            nota.registrarNota();
        } else {
            System.out.println("Professor não está autorizado a lançar notas nesta disciplina.");
        }
    }

    public void excluirProfessor(int professorId) throws SQLException {
        professorDAO.excluirProfessor(professorId);
    }

}
