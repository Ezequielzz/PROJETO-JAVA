package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.*; // Importa todas as classes do modelo
import com.br.ezequielzz.Model.DAOs.ProfessorDAO; // Importa a classe ProfessorDAO para acessar dados de professores

import java.sql.SQLException; // Importa a classe SQLException para manipulação de exceções SQL
import java.util.Date; // Importa a classe Date para trabalhar com datas
import java.util.List; // Importa a classe List para trabalhar com listas de objetos

// Controlador que gerencia operações relacionadas aos professores
public class ProfessorController {
    private ProfessorDAO professorDAO; // Instância do DAO para professores

    // Construtor do ProfessorController
    public ProfessorController() {
        this.professorDAO = new ProfessorDAO(); // Inicializa o DAO de professores
    }

    // Método para criar um novo professor
    public boolean criarProfessor(Professor professor) {
        try {
            professorDAO.criarProfessor(professor); // Chama o método do DAO para criar o professor
            return true;  // Retorna verdadeiro se a operação for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
            return false;  // Retorna falso se a operação falhar
        }
    }

    // Método para listar todos os professores
    public List<Professor> listarTodosProfessores() {
        return professorDAO.listarTodos(); // Retorna a lista de todos os professores
    }

    // Método para atribuir uma disciplina ao Professor
    public void atribuirDisciplina(Professor professor, Disciplina disciplina) {
        if (!professor.getDisciplinas().contains(disciplina)) { // Verifica se a disciplina já não está atribuída
            professor.getDisciplinas().add(disciplina); // Adiciona a disciplina à lista de disciplinas do professor
            // Chamando a DAO para persistir a atribuição
            professorDAO.atribuirDisciplina(professor, disciplina); // Chama o método do DAO para atribuir a disciplina
            System.out.println("Disciplina atribuída ao professor com sucesso."); // Exibe mensagem de sucesso
        } else {
            System.out.println("Disciplina já atribuída ao professor."); // Exibe mensagem se a disciplina já estiver atribuída
        }
    }

    // Método para lançar nota de um aluno em uma disciplina específica
    public void lancarNota(Professor professor, Aluno aluno, Disciplina disciplina, float valorNota) {
        if (professor.getDisciplinas().contains(disciplina)) { // Verifica se o professor pode lançar a nota na disciplina
            Nota nota = new Nota(0, aluno.getId(), disciplina.getId(), valorNota, new Date()); // Cria uma nova instância de Nota
            // Aqui chamamos o DAO para persistir a nota no banco
            nota.registrarNota(); // Chama o método para registrar a nota
        } else {
            System.out.println("Professor não está autorizado a lançar notas nesta disciplina."); // Exibe mensagem de erro
        }
    }

    // Método para excluir um professor pelo ID
    public void excluirProfessor(int professorId) throws SQLException {
        professorDAO.excluirProfessor(professorId); // Chama o método do DAO para excluir o professor
    }

    // Método para atualizar os dados de um professor
    public void atualizarProfessor(Professor professor) throws SQLException {
        professorDAO.atualizarProfessor(professor); // Chama o método do DAO para atualizar o professor
    }

    // Método para buscar um professor pelo ID
    public Professor buscarProfessorPorId(int id) throws SQLException {
        return professorDAO.buscarProfessorPorId(id); // Retorna o professor correspondente ao ID fornecido
    }
} 
