package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Nota {
    private int id;
    private int alunoId;
    private int disciplinaId;
    private float valorNota;
    private Date data;

    public Nota(int id, int alunoId, int disciplinaId, float valorNota, Date data) {
        this.id = id;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.valorNota = valorNota;
        this.data = data;
    }

    // Método para registrar a nota no banco de dados
    public void registrarNota() {
        NotaDAO notaDAO = new NotaDAO();
        notaDAO.registrarNota(this);
    }

    // Método para atualizar a nota no banco de dados
    public void atualizarNota() {
        NotaDAO notaDAO = new NotaDAO();
        notaDAO.atualizarNota(this);
    }

}
