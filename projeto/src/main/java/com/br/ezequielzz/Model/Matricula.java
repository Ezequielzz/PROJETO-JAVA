package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
// Classe Matricula que representa a matrícula de um aluno em uma turma
public class Matricula {
    // Atributo que armazena o ID único da matrícula
    private int id;
    
    // Atributo que armazena o ID do aluno associado à matrícula (chave estrangeira para Aluno)
    private int alunoId; // FK para Aluno
    
    // Atributo que armazena o ID da turma associada à matrícula (chave estrangeira para Turma)
    private int turmaId; // FK para Turma
    
    // Atributo que armazena a data em que a matrícula foi realizada
    private Date dataMatricula;
    
    // Atributo que representa o status da matrícula, podendo ser 'matriculado', 'cancelado' ou 'pendente'
    private String status; // 'matriculado', 'cancelado', 'pendente'

    // Construtor da classe Matricula que inicializa os campos id, alunoId, turmaId, dataMatricula e status
    public Matricula(int id, int alunoId, int turmaId, Date dataMatricula, String status) {
        this.id = id; // Define o ID da matrícula
        this.alunoId = alunoId; // Define o aluno associado à matrícula
        this.turmaId = turmaId; // Define a turma associada à matrícula
        this.dataMatricula = dataMatricula; // Define a data da matrícula
        this.status = status; // Define o status da matrícula
    }
}
