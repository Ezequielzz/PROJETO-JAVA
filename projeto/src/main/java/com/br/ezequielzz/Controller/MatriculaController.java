package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Matricula; // Importa a classe Matricula do modelo
import com.br.ezequielzz.Model.DAOs.MatriculaDAO; // Importa a classe MatriculaDAO para acessar dados de matrículas

// Controlador que gerencia operações relacionadas às matrículas
public class MatriculaController {
    private MatriculaDAO matriculaDAO; // Instância do DAO para matrículas

    // Construtor do MatriculaController
    public MatriculaController() {
        this.matriculaDAO = new MatriculaDAO(); // Inicializa o DAO de matrículas
    }

    // Método para realizar a matrícula de um aluno
    public void realizarMatricula(Matricula matricula) {
        matriculaDAO.realizarMatricula(matricula); // Chama o método do DAO para realizar a matrícula
    }

    // Método para cancelar a matrícula de um aluno
    public void cancelarMatricula(int alunoId) {
        matriculaDAO.cancelarMatricula(alunoId); // Chama o método do DAO para cancelar a matrícula do aluno
    }
} 
