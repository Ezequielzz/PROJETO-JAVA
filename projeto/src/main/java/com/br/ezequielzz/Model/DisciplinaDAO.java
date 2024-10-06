package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Database.ConnectionFactory;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void criarDisciplina(Disciplina disciplina) {
        String sql = "INSERT INTO Disciplina (nome, turma_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getTurmaId());
            stmt.executeUpdate();

            // Recupera o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                disciplina.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Nota> consultarNotasAluno(Disciplina disciplina, Aluno aluno) {
        String sql = "SELECT * FROM Nota WHERE aluno_id = ? AND disciplina_id = ?";
        List<Nota> notas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, disciplina.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("disciplina_id"),
                        rs.getFloat("valor_nota"),
                        rs.getDate("data")
                );
                notas.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notas;
    }

    public Disciplina buscarDisciplinaPorId(int id) {
        String sql = "SELECT * FROM Disciplina WHERE id = ?";
        Disciplina disciplina = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                disciplina = new Disciplina(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("turma_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplina;
    }

    // Buscar disciplinas por turma
    public List<Disciplina> buscarDisciplinasPorTurma(int turmaId) {
        String sql = "SELECT * FROM Disciplina WHERE turma_id = ?";
        List<Disciplina> disciplinas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turmaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("turma_id")
                );
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinas;
    }
}
