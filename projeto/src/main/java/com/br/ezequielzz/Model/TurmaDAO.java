package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Database.ConnectionFactory;
import com.br.ezequielzz.Model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public void criarTurma(Turma turma) {
        String sql = "INSERT INTO Turma (serie, ano_letivo, turno, sala) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, turma.getSerie());
            stmt.setString(2, turma.getAnoLetivo());
            stmt.setString(3, turma.getTurno());
            stmt.setString(4, turma.getSala());
            stmt.executeUpdate();

            // Recupera o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                turma.setTurmaId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarAlunoNaTurma(Turma turma, Aluno aluno) {
        String sql = "UPDATE Aluno SET turma_id = ? WHERE aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turma.getTurmaId());
            stmt.setInt(2, aluno.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerAlunoDaTurma(Turma turma, Aluno aluno) {
        String sql = "UPDATE Aluno SET turma_id = NULL WHERE aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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

    public Turma buscarPorDisciplina(int disciplinaId) {
        String sql = "SELECT t.* FROM Turma t "
                + "JOIN Disciplina d ON t.turma_id = d.turma_id "
                + "WHERE d.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, disciplinaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Turma(
                        rs.getInt("turma_id"),
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
}
