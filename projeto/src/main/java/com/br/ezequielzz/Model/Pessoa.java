package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String endereco;
    private String telefone;

    public abstract void atualizarDados();

    public String formatarTelefone() {
        // Implementação da formatação do telefone
    }

    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Endereço: " + endereco + ", Telefone: " + telefone;
    }
}
