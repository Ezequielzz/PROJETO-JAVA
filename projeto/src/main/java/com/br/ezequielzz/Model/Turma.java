package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
// Classe Turma que representa uma turma de alunos
public class Turma {
    // Atributo que armazena o ID único da turma
    private int turmaId;
    
    // Atributo que armazena a série da turma
    private String serie;
    
    // Atributo que armazena o ano letivo da turma
    private String anoLetivo;
    
    // Atributo que armazena o turno da turma (manhã, tarde, noite)
    private String turno;
    
    // Atributo que armazena a sala em que a turma está alocada
    private String sala;
    
    // Lista de alunos que pertencem à turma
    private List<Aluno> alunos;
    
    // Lista de professores que lecionam na turma
    private List<Professor> professores;
    
    // Lista de disciplinas que são oferecidas na turma
    private List<Disciplina> disciplinas;

    // Construtor da classe Turma que inicializa os atributos da turma
    public Turma(int turmaId, String serie, String anoLetivo, String turno, String sala) {
        this.turmaId = turmaId; // Define o ID da turma
        this.serie = serie; // Define a série da turma
        this.anoLetivo = anoLetivo; // Define o ano letivo da turma
        this.turno = turno; // Define o turno da turma
        this.sala = sala; // Define a sala da turma
        this.alunos = new ArrayList<>(); // Inicializa a lista de alunos
        this.professores = new ArrayList<>(); // Inicializa a lista de professores
        this.disciplinas = new ArrayList<>(); // Inicializa a lista de disciplinas
    }

    // Método sobrescrito para fornecer uma representação em string da turma
    @Override
    public String toString() {
        return "Turma ID: " + turmaId + " - Nome: " + serie; // Retorna informações da turma
    }
}
