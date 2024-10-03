package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Turma;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void criarAluno(Aluno aluno) {
        String sql = "INSERT INTO Aluno (pessoa_id, serie, turma_id, statusMatricula) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configurar os parâmetros para inserção na tabela Aluno
            stmt.setInt(1, aluno.getId()); // `pessoa_id` vem da superclasse Pessoa
            stmt.setString(2, aluno.getSerie());
            stmt.setInt(3, aluno.getTurmaId());
            stmt.setString(4, aluno.getStatusMatricula());

            // Executar a inserção
            stmt.executeUpdate();
            System.out.println("Aluno criado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> consultarHistorico(int alunoId) {
        List<String> historico = new ArrayList<>();
        String sql = "SELECT * FROM Historico WHERE aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Supondo que tenha uma coluna de disciplinas e notas
                String disciplina = rs.getString("disciplina");
                float nota = rs.getFloat("nota");
                historico.add(disciplina + ": " + nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historico;
    }
}
