package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Aluno extends Pessoa {
    private int alunoId;
    private String serie;
    private int turmaId;
    private String statusMatricula;

    public Aluno(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String senha, int alunoId, String serie, int turmaId, String statusMatricula) {
        super(id, nome, cpf, dataNascimento, endereco, telefone, senha); // Parâmetros de Pessoa
        this.alunoId = alunoId;
        this.serie = serie;
        this.turmaId = turmaId;
        this.statusMatricula = statusMatricula;
    }

    @Override
    public void atualizarDados() {
        // Implementar lógica de atualização de dados, como nome, endereço, etc.
    }
}
