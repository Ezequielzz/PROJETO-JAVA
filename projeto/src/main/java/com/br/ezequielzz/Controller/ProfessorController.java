package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfessorController {
    Professor professor;
    private ProfessorDAO professorDAO;

    public ProfessorController() {
        professorDAO = new ProfessorDAO();
    }

    public void criarProfessor(Professor professor) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        int pessoaId = pessoaDAO.criarPessoa(professor);  // Cria a pessoa no banco e retorna o ID gerado

        if (pessoaId != -1) {
            professor.setProfessorId(pessoaId);  // Atribui o ID da pessoa como o ID do professor
            ProfessorDAO professorDAO = new ProfessorDAO();
            professorDAO.criarProfessor(professor);  // Cria o professor usando o ID da pessoa
        } else {
            System.out.println("Erro ao criar a pessoa no banco de dados.");
        }
    }

//    // Método para atribuir uma disciplina ao Professor
//    public void atribuirDisciplina(Disciplina disciplina) {
//        if (!disciplinas.contains(disciplina)) {
//            disciplinas.add(disciplina);
//            // Chamando a DAO para persistir a atribuição
//            ProfessorDAO professorDAO = new ProfessorDAO();
//            professorDAO.atribuirDisciplina(this, disciplina);
//            System.out.println("Disciplina atribuída ao professor com sucesso.");
//        } else {
//            System.out.println("Disciplina já atribuída ao professor.");
//        }
//    }

//    // Método para consultar as turmas do professor
//    public List<Turma> consultarTurmas() {
//        List<Turma> turmas = new ArrayList<>();
//
//        for (Disciplina disciplina : disciplinas) {
//            Turma turma = new Turma(); // Cria uma instância de Turma
//            Turma turmaConsultada = turma.buscarPorDisciplina(disciplina.getId()); // Chamando método de Turma
//            if (turmaConsultada != null && !turmas.contains(turmaConsultada)) {
//                turmas.add(turmaConsultada);
//            }
//        }
//
//        return turmas;
//    }

//    // Método para lançar nota de um aluno em uma disciplina específica
//    public void lancarNota(Aluno aluno, Disciplina disciplina, float valorNota) {
//        if (disciplinas.contains(disciplina)) {
//            Nota nota = new Nota(0, aluno.getAlunoId(), disciplina.getId(), valorNota, new Date());
//            // Aqui chamamos o DAO para persistir a nota no banco
//            nota.registrarNota();
//        } else {
//            System.out.println("Professor não está autorizado a lançar notas nesta disciplina.");
//        }
//    }

}
