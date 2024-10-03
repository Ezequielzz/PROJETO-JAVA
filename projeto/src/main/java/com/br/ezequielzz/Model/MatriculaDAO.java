package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatriculaDAO {

    public void realizarMatricula(Matricula matricula) {
        String sql = "INSERT INTO Matricula (aluno_id, turma_id, data_matricula, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(2, matricula.getTurmaId());
            stmt.setDate(3, new java.sql.Date(matricula.getDataMatricula().getTime()));
            stmt.setString(4, matricula.getStatus());
            stmt.executeUpdate();

            System.out.println("Matrícula realizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelarMatricula(Matricula matricula) {
        String sql = "UPDATE Matricula SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula.getStatus());
            stmt.setInt(2, matricula.getId());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Matrícula cancelada com sucesso!");
            } else {
                System.out.println("Falha ao cancelar a matrícula.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
