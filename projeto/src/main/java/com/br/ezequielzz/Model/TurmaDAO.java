package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public void criarTurma(Turma turma) {
        String sqlTurma = "INSERT INTO Turma (serie, ano_letivo, turno, sala) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            // Inserir a turma
            try (PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmtTurma.setString(1, turma.getSerie());
                stmtTurma.setString(2, turma.getAnoLetivo());
                stmtTurma.setString(3, turma.getTurno());
                stmtTurma.setString(4, turma.getSala());
                stmtTurma.executeUpdate();

                // Recupera o ID da turma gerada
                ResultSet rs = stmtTurma.getGeneratedKeys();
                if (rs.next()) {
                    turma.setTurmaId(rs.getInt(1));
                }
            }

            // Confirma a transação se tudo estiver correto
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public List<Turma> listarTodasTurmas() {
        String sql = "SELECT * FROM Turma";
        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma turma = new Turma(
                        rs.getInt("id"),
                        rs.getString("serie"),
                        rs.getString("ano_letivo"),
                        rs.getString("turno"),
                        rs.getString("sala")
                );
                turmas.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turmas;
    }

    public void adicionarAlunoNaTurma(Turma turma, Aluno aluno) {
        String sql = "INSERT INTO Matricula (aluno_id, turma_id, data_matricula, status) VALUES (?, ?, CURRENT_DATE, 'matriculado')";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, turma.getTurmaId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerAlunoDaTurma(Turma turma, Aluno aluno) {
        String sql = "UPDATE Matricula SET status = 'cancelado' WHERE aluno_id = ? AND turma_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, turma.getTurmaId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Turma buscarPorDisciplina(int disciplinaId) {
        String sql = "SELECT t.* FROM Turma t "
                + "JOIN Disciplina d ON t.id = d.turma_id "
                + "WHERE d.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, disciplinaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Turma(
                        rs.getInt("id"),
                        rs.getString("serie"),
                        rs.getString("ano_letivo"),
                        rs.getString("turno"),
                        rs.getString("sala")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluirTurma(int turmaId) throws SQLException {
        String sql = "DELETE FROM Turma WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, turmaId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhuma turma encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir turma", e);
        }
    }
}
