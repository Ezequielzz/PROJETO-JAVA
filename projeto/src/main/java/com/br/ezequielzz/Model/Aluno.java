package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
// Classe Aluno que herda de Pessoa, representando um aluno no sistema
public class Aluno extends Pessoa {
    // Atributo que armazena o ID da turma à qual o aluno pertence (chave estrangeira)
    private int turmaId;
    
    // Atributo para o status da matrícula do aluno, que pode ser 'ativo', 'desligado' ou 'transferido'
    private String statusMatricula;

    // Construtor da classe Aluno que recebe todos os parâmetros, incluindo os da classe base Pessoa
    public Aluno(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String senha, int turmaId, String statusMatricula) {
        super(id, nome, cpf, dataNascimento, endereco, telefone, senha); // Chama o construtor da classe Pessoa
        this.turmaId = turmaId; // Define a turma do aluno
        this.statusMatricula = statusMatricula; // Define o status da matrícula
    }

    // Método para atualizar dados do aluno. A lógica para atualizar campos como nome, endereço, etc., deve ser implementada aqui.
    @Override
    public void atualizarDados() {
        // Implementar lógica de atualização de dados
    }
}
