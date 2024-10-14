package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Relatorio;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatorioDAO {

    // Método para gerar um novo relatório
    public void gerarRelatorio(Relatorio relatorio) {
        String sql = "INSERT INTO Relatorio (tipo_relatorio, data_geracao, dados_relatorio) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, relatorio.getTipoRelatorio());
            stmt.setDate(2, new java.sql.Date(relatorio.getDataGeracao().getTime()));
            stmt.setString(3, relatorio.getDadosRelatorio());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                relatorio.setId(rs.getInt(1));
            }

            System.out.println("Relatório gerado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um relatório pelo ID
    public Relatorio buscarRelatorioPorId(int id) {
        String sql = "SELECT * FROM Relatorio WHERE id = ?";
        Relatorio relatorio = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                relatorio = new Relatorio(
                        rs.getInt("id"),
                        rs.getString("tipo_relatorio"),
                        rs.getDate("data_geracao"),
                        rs.getString("dados_relatorio")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorio;
    }

    // Método para listar todos os relatórios de um tipo específico
    public List<Relatorio> listarRelatoriosPorTipo(String tipoRelatorio) {
        String sql = "SELECT * FROM Relatorio WHERE tipo_relatorio = ?";
        List<Relatorio> relatorios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoRelatorio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getInt("id"),
                        rs.getString("tipo_relatorio"),
                        rs.getDate("data_geracao"),
                        rs.getString("dados_relatorio")
                );
                relatorios.add(relatorio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorios;
    }

    // Método para gerar um boletim
    public String gerarBoletim(int alunoId) {
        String boletim = "";
        String sql = "SELECT Disciplina.nome, Nota.valor_nota " +
                "FROM Nota " +
                "JOIN Disciplina ON Nota.disciplina_id = Disciplina.id " +
                "WHERE Nota.aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            sb.append("Boletim do Aluno ID: ").append(alunoId).append("\n");

            while (rs.next()) {
                sb.append("Disciplina: ").append(rs.getString("nome"))
                        .append(" - Nota: ").append(rs.getDouble("valor_nota"))
                        .append("\n");
            }

            boletim = sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boletim;
    }

    // Método para gerar relatório de frequência
    public String gerarRelatorioFrequencia(int alunoId) {
        String frequencia = "";
        String sql = "SELECT Disciplina.nome, Frequencia.presenca, Frequencia.data " +
                "FROM Frequencia " +
                "JOIN Disciplina ON Frequencia.disciplina_id = Disciplina.id " +
                "WHERE Frequencia.aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            sb.append("Relatório de Frequência do Aluno ID: ").append(alunoId).append("\n");

            while (rs.next()) {
                sb.append("Disciplina: ").append(rs.getString("nome"))
                        .append(" - Presença: ").append(rs.getBoolean("presenca") ? "Presente" : "Ausente")
                        .append(" - Data: ").append(rs.getDate("data"))
                        .append("\n");
            }

            frequencia = sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return frequencia;
    }
}
