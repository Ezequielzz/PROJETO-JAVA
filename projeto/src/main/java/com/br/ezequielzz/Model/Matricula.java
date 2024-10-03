package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Matricula {
    private int id;
    private int alunoId; // FK para Aluno
    private int turmaId; // FK para Turma
    private Date dataMatricula;
    private String status; // 'matriculado', 'cancelado', 'pendente'

    public Matricula(int id, int alunoId, int turmaId, Date dataMatricula, String status) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.dataMatricula = dataMatricula;
        this.status = status;
    }

    // Método para realizar a matrícula de um aluno
    public void realizarMatricula() {
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        this.status = "matriculado"; // Define o status como matriculado
        matriculaDAO.realizarMatricula(this);
    }

    // Método para cancelar a matrícula de um aluno
    public void cancelarMatricula() {
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        this.status = "cancelado"; // Define o status como cancelado
        matriculaDAO.cancelarMatricula(this);
    }

}
