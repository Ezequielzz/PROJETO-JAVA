package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Aluno;
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

    public List<Aluno> listarTodos() {
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

    public void excluirAluno(int alunoId) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum aluno encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir aluno", e);
        }
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, data_nascimento = ?, endereco = ?, telefone = ?, senha = ?, turma_id = ?, status_matricula = ? WHERE id = ?";

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
            stmt.setInt(9, aluno.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum aluno encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao atualizar aluno", e);
        }
    }

    public Aluno buscarAlunoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Aluno aluno = null;
    
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("data_nascimento"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setTurmaId(rs.getInt("turma_id"));
                aluno.setStatusMatricula(rs.getString("status_matricula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao buscar aluno por ID", e);
        }
    
        return aluno;
    }
    

}
