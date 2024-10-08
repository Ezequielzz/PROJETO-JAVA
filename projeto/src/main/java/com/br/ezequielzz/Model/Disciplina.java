package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Disciplina {
    private int id;
    private String nome;
    private int turmaId; // FK para Turma
    private int professorId; // FK para Professor

    public Disciplina(int id, String nome, int turmaId, int professorId) {
        this.id = id;
        this.nome = nome;
        this.turmaId = turmaId;
        this.professorId = professorId;
    }

    @Override
    public String toString() {
        return "Disciplina ID: " + id + " - Nome: " + nome;
    }
}
