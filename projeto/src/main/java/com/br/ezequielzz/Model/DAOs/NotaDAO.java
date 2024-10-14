package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Database.ConnectionFactory;
import com.br.ezequielzz.Model.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

public class NotaDAO {

    public void registrarNota(Nota nota) {
        String sql = "INSERT INTO Nota (aluno_id, disciplina_id, valor_nota, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, nota.getAlunoId());
            stmt.setInt(2, nota.getDisciplinaId());
            stmt.setFloat(3, nota.getValorNota());
            stmt.setDate(4, new java.sql.Date(nota.getData().getTime()));
            stmt.executeUpdate();

            // Recupera o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                nota.setId(rs.getInt(1));
            }

            System.out.println("Nota registrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNota(Nota nota) {
        String sql = "UPDATE Nota SET valor_nota = ?, data = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, nota.getValorNota());
            stmt.setDate(2, new java.sql.Date(nota.getData().getTime()));
            stmt.setInt(3, nota.getId());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Nota atualizada com sucesso!");
            } else {
                System.out.println("Falha ao atualizar a nota.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Nota buscarNotaPorId(int id) {
        String sql = "SELECT * FROM Nota WHERE id = ?";
        Nota nota = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nota = new Nota(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("disciplina_id"),
                        rs.getFloat("valor_nota"),
                        rs.getDate("data")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nota;
    }
}
