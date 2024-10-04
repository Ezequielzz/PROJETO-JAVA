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
}
