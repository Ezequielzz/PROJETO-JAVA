package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Professor;
import com.br.ezequielzz.Model.Turma;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    public void criarProfessor(Professor professor) {
        String sql = "INSERT INTO Professor (pessoa_id) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura os parâmetros da query
            stmt.setInt(1, professor.getId()); // `pessoa_id` vem da superclasse Pessoa

            // Executa a inserção no banco de dados
            stmt.executeUpdate();
            System.out.println("Professor criado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atribuirDisciplina(Professor professor, Disciplina disciplina) {
        String sql = "INSERT INTO ProfessorDisciplina (professor_id, disciplina_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professor.getId());
            stmt.setInt(2, disciplina.getId());

            stmt.executeUpdate();
            System.out.println("Disciplina atribuída com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

