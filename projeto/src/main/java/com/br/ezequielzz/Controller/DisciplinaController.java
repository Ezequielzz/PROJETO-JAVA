package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.DisciplinaDAO;
import com.br.ezequielzz.Model.Nota;

import java.util.List;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController() {
        this.disciplinaDAO = new DisciplinaDAO();
    }

    // Criar uma nova disciplina
    public void criarDisciplina(Disciplina disciplina) {
        disciplinaDAO.criarDisciplina(disciplina);
    }

    // Consultar notas de um aluno para uma disciplina
    public List<Nota> consultarNotas(Disciplina disciplina, Aluno aluno) {
        return disciplinaDAO.consultarNotasAluno(disciplina, aluno);
    }

    // Buscar disciplina por ID
    public Disciplina buscarDisciplinaPorId(int id) {
        return disciplinaDAO.buscarDisciplinaPorId(id);
    }

    // Buscar disciplinas por turma
    public List<Disciplina> buscarDisciplinasPorTurma(int turmaId) {
        return disciplinaDAO.buscarDisciplinasPorTurma(turmaId);
    }
}
