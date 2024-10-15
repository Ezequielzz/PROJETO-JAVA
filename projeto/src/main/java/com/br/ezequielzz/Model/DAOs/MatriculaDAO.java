package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Matricula;
import com.br.ezequielzz.Model.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class MatriculaDAO {

    public void realizarMatricula(Matricula matricula) {
        String sql = "INSERT INTO Matricula (aluno_id, turma_id, data_matricula, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(2, matricula.getTurmaId());
            stmt.setDate(3, new Date(matricula.getDataMatricula().getTime()));
            stmt.setString(4, matricula.getStatus());
            stmt.executeUpdate();

            System.out.println("Matrícula realizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelarMatricula(int alunoId) {
        String sql = "UPDATE Matricula SET status = ? WHERE aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "cancelado");
            stmt.setInt(2, alunoId);
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
