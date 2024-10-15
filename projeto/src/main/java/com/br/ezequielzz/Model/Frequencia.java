package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
// Classe Frequencia representando o registro de presença de um aluno em uma disciplina
public class Frequencia {
    // Atributo que armazena o ID único do registro de frequência
    private int id;
    
    // Atributo para o ID do aluno associado à frequência (chave estrangeira para Aluno)
    private int alunoId;
    
    // Atributo para o ID da disciplina associada à frequência (chave estrangeira para Disciplina)
    private int disciplinaId;
    
    // Atributo que armazena a data da frequência
    private Date data;
    
    // Atributo booleano que indica se o aluno esteve presente ou ausente
    private boolean presenca;

    // Construtor da classe Frequencia que inicializa os campos id, alunoId, disciplinaId, data e presença
    public Frequencia(int id, int alunoId, int disciplinaId, Date data, boolean presenca) {
        this.id = id;
        this.alunoId = alunoId; // Define o aluno associado à frequência
        this.disciplinaId = disciplinaId; // Define a disciplina associada à frequência
        this.data = data; // Define a data da frequência
        this.presenca = presenca; // Define se o aluno estava presente
    }
}
