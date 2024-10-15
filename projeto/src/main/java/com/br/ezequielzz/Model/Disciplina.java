package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Classe Disciplina representando uma disciplina no sistema
public class Disciplina {
    // Atributo que armazena o ID único da disciplina
    private int id;
    
    // Atributo para o nome da disciplina
    private String nome;
    
    // Atributo que armazena o ID da turma associada à disciplina (chave estrangeira)
    private int turmaId;

    // Construtor da classe Disciplina que inicializa os campos ID, nome e turmaId
    public Disciplina(int id, String nome, int turmaId) {
        this.id = id;
        this.nome = nome;
        this.turmaId = turmaId; // Define a turma à qual a disciplina está associada
    }

    // Método sobrescrito para fornecer uma representação em string da disciplina
    @Override
    public String toString() {
        return "Disciplina ID: " + id + " - Nome: " + nome;
    }
}
