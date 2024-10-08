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
        String sqlTurmaProfessor = "INSERT INTO Turma_Professor (turma_id, professor_id) VALUES (?, ?)";

        Connection conn = null;
        try {
            // Abre a conexão fora do try-with-resources
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false); // Desabilita autocommit para trabalhar com transações

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

            // Inserir professores na tabela intermediária
            try (PreparedStatement stmtTurmaProfessor = conn.prepareStatement(sqlTurmaProfessor)) {
                for (Professor professor : turma.getProfessores()) {
                    stmtTurmaProfessor.setInt(1, turma.getTurmaId());
                    stmtTurmaProfessor.setInt(2, professor.getId());
                    stmtTurmaProfessor.addBatch();  // Adiciona ao batch
                }
                stmtTurmaProfessor.executeBatch();  // Executa todas as inserções em lote
            }

            // Confirma a transação se tudo estiver correto
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Reverte a transação no caso de erro
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaura o autocommit ao valor padrão
                    conn.close();  // Fecha a conexão
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void adicionarProfessorATurma(Turma turma, Professor professor) {
        String sql = "INSERT INTO turma_professor (turma_id, professor_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turma.getTurmaId());
            stmt.setInt(2, professor.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void adicionarProfessorNaTurma(int turmaId, int professorId) {
        String sql = "UPDATE Turma SET professor_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professorId);
            stmt.setInt(2, turmaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Turma> listarTodasTurmas() {
        String sql = "SELECT t.*, p.id AS professor_id, p.nome AS professor_nome FROM Turma t "
                + "LEFT JOIN Turma_Professor tp ON t.id = tp.turma_id "
                + "LEFT JOIN Professor p ON tp.professor_id = p.id";
        List<Turma> turmas = new ArrayList<>();
        Turma turmaAtual = null;
        int turmaIdAtual = -1;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int turmaId = rs.getInt("id");

                // Se a turma atual é diferente da do próximo resultado, cria uma nova Turma
                if (turmaId != turmaIdAtual) {
                    turmaAtual = new Turma(
                            turmaId,
                            rs.getString("serie"),
                            rs.getString("ano_letivo"),
                            rs.getString("turno"),
                            rs.getString("sala")
                    );
                    turmas.add(turmaAtual);
                    turmaIdAtual = turmaId;
                }

                // Se houver um professor associado, adicione à lista de professores
                if (rs.getInt("professor_id") > 0) {
                    Professor professor = new Professor();
                    professor.setId(rs.getInt("professor_id"));
                    professor.setNome(rs.getString("professor_nome"));
                    turmaAtual.getProfessores().add(professor);
                }
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
        String sql = "DELETE FROM turma WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, turmaId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum turma encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir turma", e);
        }
    }
}
