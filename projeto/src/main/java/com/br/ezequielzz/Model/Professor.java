package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Professor extends Pessoa {
    private int professorId;
    List<Disciplina> disciplinas;

    // Construtor
    public Professor(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String senha) {
        super(id, nome, cpf, dataNascimento, endereco, telefone, senha);
        this.disciplinas = new ArrayList<>(); // Inicializando a Lista de Disciplinas do Professor
    }


    @Override
    public void atualizarDados() {
        // Implementação para atualizar os dados do professor no banco de dados
        // Aqui pode ser feita uma chamada para o DAO responsável pela atualização
    }
}
