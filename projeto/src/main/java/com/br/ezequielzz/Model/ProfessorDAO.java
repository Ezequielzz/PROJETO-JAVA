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
        String sql = "INSERT INTO Professor (nome, cpf, dataNascimento, endereco, telefone, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configurar os parâmetros para inserção na tabela Professor
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setDate(3, new java.sql.Date(professor.getDataNascimento().getTime())); // Conversão para java.sql.Date
            stmt.setString(4, professor.getEndereco());
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getSenha());

            // Executar a inserção
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

