package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Turma;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe DAO responsável pelas operações relacionadas à entidade "Turma"
public class TurmaDAO {

    // Método para criar uma nova turma no banco de dados
    public void criarTurma(Turma turma) {
        // SQL para inserir uma nova turma com série, ano letivo, turno e sala
        String sqlTurma = "INSERT INTO Turma (serie, ano_letivo, turno, sala) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        try {
            // Estabelece uma conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false); // Desativa o auto-commit para transações manuais

            // Inserir a turma
            try (PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma, PreparedStatement.RETURN_GENERATED_KEYS)) {
                // Define os parâmetros da query com os dados da turma
                stmtTurma.setString(1, turma.getSerie());
                stmtTurma.setString(2, turma.getAnoLetivo());
                stmtTurma.setString(3, turma.getTurno());
                stmtTurma.setString(4, turma.getSala());
                stmtTurma.executeUpdate(); // Executa a inserção

                // Recupera o ID gerado automaticamente para a turma
                ResultSet rs = stmtTurma.getGeneratedKeys();
                if (rs.next()) {
                    turma.setTurmaId(rs.getInt(1)); // Define o ID da turma gerada
                }
            }

            // Confirma a transação se tudo estiver correto
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro em caso de falha
            if (conn != null) {
                try {
                    conn.rollback(); // Desfaz a transação em caso de erro
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaura o auto-commit
                    conn.close(); // Fecha a conexão
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    // Método para listar todas as turmas no banco de dados
    public List<Turma> listarTodasTurmas() {
        // SQL para selecionar todas as turmas
        String sql = "SELECT * FROM Turma";
        List<Turma> turmas = new ArrayList<>();

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados e cria objetos Turma com os dados retornados
            while (rs.next()) {
                Turma turma = new Turma(
                        rs.getInt("id"),        // ID da turma
                        rs.getString("serie"),  // Série da turma
                        rs.getString("ano_letivo"), // Ano letivo da turma
                        rs.getString("turno"),  // Turno da turma
                        rs.getString("sala")    // Sala da turma
                );
                turmas.add(turma); // Adiciona a turma à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro em caso de falha
        }
        return turmas; // Retorna a lista de turmas
    }

    // Método para adicionar um aluno a uma turma (matrícula)
    public void adicionarAlunoNaTurma(Turma turma, Aluno aluno) {
        // SQL para inserir uma nova matrícula
        String sql = "INSERT INTO Matricula (aluno_id, turma_id, data_matricula, status) VALUES (?, ?, CURRENT_DATE, 'matriculado')";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da query com o ID do aluno e da turma
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, turma.getTurmaId());
            stmt.executeUpdate(); // Executa a inserção

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro em caso de falha
        }
    }

    // Método para remover um aluno de uma turma (cancelamento de matrícula)
    public void removerAlunoDaTurma(Turma turma, Aluno aluno) {
        // SQL para atualizar o status da matrícula para "cancelado"
        String sql = "UPDATE Matricula SET status = 'cancelado' WHERE aluno_id = ? AND turma_id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da query com o ID do aluno e da turma
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, turma.getTurmaId());
            stmt.executeUpdate(); // Executa a atualização

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro em caso de falha
        }
    }

    // Método para buscar uma turma associada a uma disciplina específica
    public Turma buscarPorDisciplina(int disciplinaId) {
        // SQL para buscar a turma que contém a disciplina fornecida
        String sql = "SELECT t.* FROM Turma t "
                + "JOIN Disciplina d ON t.id = d.turma_id "
                + "WHERE d.id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o parâmetro da query com o ID da disciplina
            stmt.setInt(1, disciplinaId);
            ResultSet rs = stmt.executeQuery();

            // Se a turma for encontrada, cria um objeto Turma com os dados retornados
            if (rs.next()) {
                return new Turma(
                        rs.getInt("id"),         // ID da turma
                        rs.getString("serie"),   // Série da turma
                        rs.getString("ano_letivo"), // Ano letivo
                        rs.getString("turno"),   // Turno
                        rs.getString("sala")     // Sala
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro em caso de falha
        }
        return null; // Retorna null se nenhuma turma for encontrada
    }

    // Método para buscar a turma de um aluno específico
    public Turma buscarTurmaPorAluno(int alunoId) throws SQLException {
        // SQL para buscar a turma associada ao aluno fornecido
        String sql = "SELECT t.* FROM Turma t "
                   + "JOIN Aluno a ON t.id = a.turma_id "
                   + "WHERE a.id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            // Define o parâmetro da query com o ID do aluno
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            // Se a turma for encontrada, cria um objeto Turma com os dados retornados
            if (rs.next()) {
                return new Turma(
                        rs.getInt("id"),         // ID da turma
                        rs.getString("serie"),   // Série da turma
                        rs.getString("ano_letivo"), // Ano letivo
                        rs.getString("turno"),   // Turno
                        rs.getString("sala")     // Sala
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro e lança uma exceção personalizada
            throw new SQLException("Erro ao buscar turma para o aluno", e);
        }
        return null; // Retorna null se nenhuma turma for encontrada para o aluno
    }

    // Método para excluir uma turma do banco de dados
    public void excluirTurma(int turmaId) throws SQLException {
        // SQL para excluir uma turma pelo seu ID
        String sql = "DELETE FROM Turma WHERE id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define o parâmetro da query com o ID da turma
            stmt.setInt(1, turmaId);
            int rowsAffected = stmt.executeUpdate(); // Executa a exclusão

            // Verifica se alguma linha foi afetada (se a turma foi encontrada)
            if (rowsAffected == 0) {
                throw new SQLException("Nenhuma turma encontrada com o ID fornecido."); // Lança uma exceção se nenhuma turma for encontrada
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro e lança uma exceção personalizada
            throw new SQLException("Erro ao excluir turma", e);
        }
    }
}
