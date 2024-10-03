package com.br.ezequielzz.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public abstract class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String endereco;
    private String telefone;
    private String senha;

    public abstract void atualizarDados();

    public String formatarTelefone() {
       if(telefone != null && telefone.length() == 10) {
           return String.format("(%s) %s-%s", telefone.substring(0, 2), telefone.substring(2, 6), telefone.substring(6));
       } else if (telefone != null && telefone.length() == 11) {
           return String.format("(%s) %s-%s", telefone.substring(0, 2), telefone.substring(2, 7), telefone.substring(7));
       }
       return telefone;
    }

    public String formatarCpf() {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Endereço: " + endereco + ", Telefone: " + telefone;
    }
}
