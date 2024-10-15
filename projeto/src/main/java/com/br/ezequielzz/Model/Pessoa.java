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
// Classe abstrata Pessoa que representa uma pessoa no sistema
public abstract class Pessoa {
    // Atributo que armazena o ID único da pessoa
    private int id;
    
    // Atributo que armazena o nome da pessoa
    private String nome;
    
    // Atributo que armazena o CPF da pessoa
    private String cpf;
    
    // Atributo que armazena a data de nascimento da pessoa
    private Date dataNascimento;
    
    // Atributo que armazena o endereço da pessoa
    private String endereco;
    
    // Atributo que armazena o telefone da pessoa
    private String telefone;
    
    // Atributo que armazena a senha da pessoa
    private String senha;

    // Método abstrato que deve ser implementado por subclasses para atualizar dados da pessoa
    public abstract void atualizarDados();

    // Método sobrescrito para fornecer uma representação em string da pessoa
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Endereço: " + endereco + ", Telefone: " + telefone;
    }
}
