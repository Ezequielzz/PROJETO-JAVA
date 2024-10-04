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

    public Disciplina(int id, String nome, int turmaId) {
        this.id = id;
        this.nome = nome;
        this.turmaId = turmaId;
    }
}
