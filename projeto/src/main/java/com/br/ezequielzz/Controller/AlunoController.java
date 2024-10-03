package com.br.ezequielzz.Controller;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.AlunoDAO;
import com.br.ezequielzz.Model.PessoaDAO;

import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    public void criarAluno(Aluno aluno) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        int pessoaId = pessoaDAO.criarPessoa(aluno);  // Cria a pessoa no banco e retorna o ID gerado

        if (pessoaId != -1) {
            aluno.setAlunoId(pessoaId);  // Atribui o ID da pessoa como o ID do aluno
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.criarAluno(aluno);  // Cria o aluno usando o ID da pessoa
        } else {
            System.out.println("Erro ao criar a pessoa no banco de dados.");
        }
    }

    public List<String> consultarHistorico(int alunoId) {
        // Chama o DAO para consultar o histórico no banco
        return alunoDAO.consultarHistorico(alunoId);
    }
}
