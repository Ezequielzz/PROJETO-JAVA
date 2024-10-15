package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Pessoa {
    private int turmaId;
    private String statusMatricula; // 'ativo', 'desligado', 'transferido'

    public Aluno(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String senha, int turmaId, String statusMatricula) {
        super(id, nome, cpf, dataNascimento, endereco, telefone, senha); // Parâmetros de Pessoa
        this.turmaId = turmaId;
        this.statusMatricula = statusMatricula;
    }

    @Override
    public void atualizarDados() {
        // Implementar lógica de atualização de dados, como nome, endereço, etc.
    }
}
