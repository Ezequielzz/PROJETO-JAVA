package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void criarAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, cpf, data_nascimento, endereco, telefone, senha, turma_id, status_matricula) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setString(4, aluno.getEndereco());
            stmt.setString(5, aluno.getTelefone());
            stmt.setString(6, aluno.getSenha());
            stmt.setInt(7, aluno.getTurmaId());
            stmt.setString(8, aluno.getStatusMatricula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao inserir aluno", e);
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

    public java.util.List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno"; // Ajuste para os campos corretos

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("senha"),
                        rs.getInt("turma_id"),
                        rs.getString("status_matricula"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public List<Aluno> listarAlunosPorTurma(int turmaId) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno WHERE turma_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turmaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("senha"),
                        rs.getInt("turma_id"),
                        rs.getString("status_matricula")

                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

}
