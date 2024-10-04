package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Frequencia {
    private int id;
    private int alunoId; // FK para Aluno
    private int disciplinaId; // FK para Disciplina
    private Date data;
    private boolean presenca;

    public Frequencia(int id, int alunoId, int disciplinaId, Date data, boolean presenca) {
        this.id = id;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.data = data;
        this.presenca = presenca;
    }
}
