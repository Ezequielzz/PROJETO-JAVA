package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Turma {
    private int turmaId;
    private String serie;
    private String anoLetivo;
    private String turno;
    private String sala;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> disciplinas;

    public Turma(int turmaId, String serie, String anoLetivo, String turno, String sala) {
        this.turmaId = turmaId;
        this.serie = serie;
        this.anoLetivo = anoLetivo;
        this.turno = turno;
        this.sala = sala;
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null) {
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.adicionarAlunoNaTurma(this, aluno);
            alunos.add(aluno);
        }
    }

    public void removerAluno(Aluno aluno) {
        if (aluno != null) {
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.removerAlunoDaTurma(this, aluno);
            alunos.remove(aluno);
        }
    }

    public void listarAlunos() {
        for (Aluno aluno : alunos) {
            System.out.println(aluno.toString());
        }
    }

    public Turma buscarPorDisciplina(int disciplinaId) {
        TurmaDAO turmaDAO = new TurmaDAO();
        return turmaDAO.buscarPorDisciplina(disciplinaId);
    }
}
