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
// Classe Professor que estende a classe abstrata Pessoa
public class Professor extends Pessoa {
    // Atributo que armazena o ID único do professor
    private int professorId;
    
    // Lista de disciplinas que o professor leciona
    List<Disciplina> disciplinas;

    // Construtor da classe Professor que inicializa os atributos herdados da classe Pessoa
    public Professor(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String senha) {
        super(id, nome, cpf, dataNascimento, endereco, telefone, senha); // Chama o construtor da classe pai
        this.disciplinas = new ArrayList<>(); // Inicializando a Lista de Disciplinas do Professor
    }

    // Método para atualizar os dados do professor
    @Override
    public void atualizarDados() {
        // Implementação para atualizar os dados do professor no banco de dados
        // Aqui pode ser feita uma chamada para o DAO responsável pela atualização
    }
}
