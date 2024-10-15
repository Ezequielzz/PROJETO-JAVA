package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno; // Importa a classe Aluno do modelo
import com.br.ezequielzz.Model.Disciplina; // Importa a classe Disciplina do modelo
import com.br.ezequielzz.Model.Nota; // Importa a classe Nota do modelo
import com.br.ezequielzz.Model.DAOs.DisciplinaDAO; // Importa a classe DisciplinaDAO para acessar dados de disciplinas

import java.util.List; // Importa a classe List para trabalhar com listas de objetos

// Controlador que gerencia operações relacionadas às disciplinas
public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO; // Instância do DAO para disciplinas

    // Construtor do DisciplinaController
    public DisciplinaController() {
        this.disciplinaDAO = new DisciplinaDAO(); // Inicializa o DAO de disciplinas
    }

    // Método para criar uma nova disciplina
    public void criarDisciplina(Disciplina disciplina) {
        disciplinaDAO.criarDisciplina(disciplina); // Chama o método do DAO para criar a disciplina
    }

    // Método para consultar notas de um aluno para uma disciplina
    public List<Nota> consultarNotas(Disciplina disciplina, Aluno aluno) {
        return disciplinaDAO.consultarNotasAluno(disciplina, aluno); // Retorna a lista de notas do aluno na disciplina especificada
    }

    // Método para buscar disciplina por ID
    public Disciplina buscarDisciplinaPorId(int id) {
        return disciplinaDAO.buscarDisciplinaPorId(id); // Retorna a disciplina correspondente ao ID fornecido
    }

    // Método para buscar disciplinas por turma
    public List<Disciplina> buscarDisciplinasPorTurma(int turmaId) {
        return disciplinaDAO.buscarDisciplinasPorTurma(turmaId); // Retorna a lista de disciplinas de uma turma específica
    }
} 
