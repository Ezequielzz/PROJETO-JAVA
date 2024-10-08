package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Professor;
import com.br.ezequielzz.Model.Turma;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void criarProfessor(Professor professor) {
        String sql = "INSERT INTO Professor (nome, cpf, data_nascimento, endereco, telefone, senha) " +
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

    public List<Professor> listarTodos() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor"; // Ajuste para os campos corretos

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("senha"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professores;
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

    public void excluirProfessor(int professorId) throws SQLException {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, professorId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum professor encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir professor", e);
        }
    }
}

