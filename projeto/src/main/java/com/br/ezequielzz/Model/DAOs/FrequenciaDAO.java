package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Frequencia;
import com.br.ezequielzz.Model.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrequenciaDAO {

    public void registrarFrequencia(Frequencia frequencia) {
        String sql = "INSERT INTO Frequencia (aluno_id, disciplina_id, data, presenca) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, frequencia.getAlunoId());
            stmt.setInt(2, frequencia.getDisciplinaId());
            stmt.setDate(3, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setBoolean(4, frequencia.isPresenca());
            stmt.executeUpdate();

            // Recupera o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                frequencia.setId(rs.getInt(1));
            }

            System.out.println("Frequência registrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarFrequencia(Frequencia frequencia) {
        String sql = "UPDATE Frequencia SET presenca = ?, data = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, frequencia.isPresenca());
            stmt.setDate(2, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setInt(3, frequencia.getId());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Frequência atualizada com sucesso!");
            } else {
                System.out.println("Falha ao atualizar a frequência.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Frequencia buscarFrequenciaPorId(int id) {
        String sql = "SELECT * FROM Frequencia WHERE id = ?";
        Frequencia frequencia = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                frequencia = new Frequencia(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("disciplina_id"),
                        rs.getDate("data"),
                        rs.getBoolean("presenca")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return frequencia;
    }

    public List<Frequencia> consultarFrequencia(int alunoId, int disciplinaId, Date dataInicial, Date dataFinal) {
        String sql = "SELECT * FROM Frequencia WHERE aluno_id = ? AND disciplina_id = ? AND data BETWEEN ? AND ?";
        List<Frequencia> frequencias = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            stmt.setInt(2, disciplinaId);
            stmt.setDate(3, new java.sql.Date(dataInicial.getTime()));
            stmt.setDate(4, new java.sql.Date(dataFinal.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Frequencia frequencia = new Frequencia(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("disciplina_id"),
                        rs.getDate("data"),
                        rs.getBoolean("presenca")
                );
                frequencias.add(frequencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return frequencias;
    }
}
