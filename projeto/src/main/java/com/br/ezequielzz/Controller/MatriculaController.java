package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Matricula;
import com.br.ezequielzz.Model.MatriculaDAO;

public class MatriculaController {
    private MatriculaDAO matriculaDAO;

    public MatriculaController() {
        this.matriculaDAO = new MatriculaDAO();
    }

    // Realizar a matrícula de um aluno
    public void realizarMatricula(Matricula matricula) {
        matriculaDAO.realizarMatricula(matricula);
    }

    // Cancelar a matrícula de um aluno
    public void cancelarMatricula(Matricula matricula) {
        matriculaDAO.cancelarMatricula(matricula);
    }
}
