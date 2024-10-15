package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.br.ezequielzz.Model.DAOs.NotaDAO;

@Getter
@Setter
// Classe Nota que representa a nota de um aluno em uma disciplina
public class Nota {
    // Atributo que armazena o ID único da nota
    private int id;
    
    // Atributo que armazena o ID do aluno associado à nota (chave estrangeira para Aluno)
    private int alunoId;
    
    // Atributo que armazena o ID da disciplina associada à nota (chave estrangeira para Disciplina)
    private int disciplinaId;
    
    // Atributo que armazena o valor da nota do aluno
    private float valorNota;
    
    // Atributo que armazena a data em que a nota foi registrada
    private Date data;

    // Construtor da classe Nota que inicializa os campos id, alunoId, disciplinaId, valorNota e data
    public Nota(int id, int alunoId, int disciplinaId, float valorNota, Date data) {
        this.id = id; // Define o ID da nota
        this.alunoId = alunoId; // Define o aluno associado à nota
        this.disciplinaId = disciplinaId; // Define a disciplina associada à nota
        this.valorNota = valorNota; // Define o valor da nota
        this.data = data; // Define a data em que a nota foi registrada
    }

    // Método para registrar a nota no banco de dados
    public void registrarNota() {
        NotaDAO notaDAO = new NotaDAO(); // Cria uma instância de NotaDAO
        notaDAO.registrarNota(this); // Chama o método para registrar a nota no banco
    }

    // Método para atualizar a nota no banco de dados
    public void atualizarNota() {
        NotaDAO notaDAO = new NotaDAO(); // Cria uma instância de NotaDAO
        notaDAO.atualizarNota(this); // Chama o método para atualizar a nota no banco
    }

}
