package com.br.ezequielzz.Model;

import java.util.Date;

public class Professor extends Pessoa{
    private int professorId;

    public Professor(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String senha) {
        super(id, nome, cpf, dataNascimento, endereco, telefone, senha);
    }

    @Override
    public void atualizarDados() {

    }
}
