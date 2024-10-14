package com.br.ezequielzz.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String endereco;
    private String telefone;
    private String senha;

    public abstract void atualizarDados();

    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Endereço: " + endereco + ", Telefone: " + telefone;
    }
}
