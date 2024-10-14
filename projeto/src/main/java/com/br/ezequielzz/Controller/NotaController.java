package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Nota;
import com.br.ezequielzz.Model.DAOs.NotaDAO;

public class NotaController {

    private NotaDAO notaDAO;

    public NotaController() {
        this.notaDAO = new NotaDAO();
    }

    public void registrarNota(int alunoId, int disciplinaId, float valorNota, java.util.Date data) {
        Nota nota = new Nota(0, alunoId, disciplinaId, valorNota, data);
        notaDAO.registrarNota(nota);
    }

    public void atualizarNota(int id, float novoValorNota, java.util.Date novaData) {
        Nota nota = notaDAO.buscarNotaPorId(id);
        if (nota != null) {
            nota.setValorNota(novoValorNota);
            nota.setData(novaData);
            notaDAO.atualizarNota(nota);
        } else {
            System.out.println("Nota não encontrada.");
        }
    }

    public Nota buscarNotaPorId(int id) {
        return notaDAO.buscarNotaPorId(id);
    }
}
