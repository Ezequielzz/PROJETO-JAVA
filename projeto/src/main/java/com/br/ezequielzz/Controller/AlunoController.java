package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno; // Importa a classe Aluno do modelo
import com.br.ezequielzz.Model.DAOs.AlunoDAO; // Importa a classe AlunoDAO para acessar dados de alunos
import com.br.ezequielzz.Model.DAOs.MatriculaDAO; // Importa a classe MatriculaDAO para acessar dados de matrículas

import java.sql.SQLException; // Importa a classe SQLException para manipulação de exceções SQL
import java.util.List; // Importa a classe List para trabalhar com listas de objetos

// Controlador que gerencia operações relacionadas aos alunos
public class AlunoController {
    private AlunoDAO alunoDAO; // Instância do DAO para alunos
    private MatriculaDAO matriculaDAO; // Instância do DAO para matrículas

    // Construtor do AlunoController
    public AlunoController() {
        this.alunoDAO = new AlunoDAO(); // Inicializa o DAO de alunos
        this.matriculaDAO = new MatriculaDAO(); // Inicializa o DAO de matrículas
    }

    // Método para criar um novo aluno
    public boolean criarAluno(Aluno aluno) {
        try {
            alunoDAO.criarAluno(aluno); // Chama o método para criar o aluno no DAO
            return true; // Retorna verdadeiro se a operação for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
            return false; // Retorna falso se a operação falhar
        }
    }

    // Método para listar todos os alunos
    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos(); // Retorna a lista de todos os alunos
    }

    // Método para listar alunos por turma
    public List<Aluno> listarAlunosPorTurma(int turmaId) {
        return alunoDAO.listarAlunosPorTurma(turmaId); // Retorna a lista de alunos de uma turma específica
    }

    // Método para excluir um aluno pelo ID
    public void excluirAluno(int alunoId) throws SQLException {
        alunoDAO.excluirAluno(alunoId); // Chama o método do DAO para excluir o aluno
    }

    // Método para atualizar os dados de um aluno
    public void atualizarAluno(Aluno aluno) throws SQLException {
        alunoDAO.atualizarAluno(aluno); // Chama o método do DAO para atualizar o aluno
    }

    // Método para buscar um aluno pelo ID
    public Aluno buscarAlunoPorId(int id) throws SQLException {
        return alunoDAO.buscarAlunoPorId(id); // Retorna o aluno correspondente ao ID fornecido
    }

    // Método para desligar um aluno (atualiza o status para "desligado")
    public void desligarAluno(int alunoId) throws SQLException {
        Aluno aluno = alunoDAO.buscarAlunoPorId(alunoId); // Busca o aluno pelo ID
        if (aluno != null) { // Verifica se o aluno foi encontrado
            aluno.setStatusMatricula("desligado"); // Atualiza o status do aluno para "desligado"
            alunoDAO.atualizarAluno(aluno); // Atualiza os dados do aluno no DAO

            // Cancelar a matrícula do aluno
            matriculaDAO.cancelarMatricula(alunoId); // Chama o método do DAO para cancelar a matrícula
        } else {
            throw new SQLException("Aluno não encontrado para desligar."); // Lança uma exceção se o aluno não for encontrado
        }
    }   

    // Método para transferir o aluno para outra turma
    public void transferirAluno(int alunoId, int novaTurmaId) throws SQLException {
        Aluno aluno = alunoDAO.buscarAlunoPorId(alunoId); // Busca o aluno pelo ID
        if (aluno != null) { // Verifica se o aluno foi encontrado
            aluno.setTurmaId(novaTurmaId); // Atualiza o ID da turma do aluno
            alunoDAO.atualizarAluno(aluno); // Atualiza os dados do aluno no DAO
        } else {
            throw new SQLException("Aluno não encontrado para transferir."); // Lança uma exceção se o aluno não for encontrado
        }
    }
} 
