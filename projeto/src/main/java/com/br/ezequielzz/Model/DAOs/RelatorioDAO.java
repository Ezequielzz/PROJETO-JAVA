package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Relatorio;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// Classe DAO responsável pelas operações relacionadas à entidade "Relatorio"
public class RelatorioDAO {

    // Método para gerar um novo relatório no banco de dados
    public void gerarRelatorio(Relatorio relatorio) {
        // SQL para inserir um novo relatório com tipo, data de geração e dados
        String sql = "INSERT INTO Relatorio (tipo_relatorio, data_geracao, dados_relatorio) VALUES (?, ?, ?)";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Configura os parâmetros da query com os dados do relatório
            stmt.setString(1, relatorio.getTipoRelatorio());
            stmt.setDate(2, new Date(relatorio.getDataGeracao().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setString(3, relatorio.getDadosRelatorio());
            
            // Executa a inserção no banco de dados
            stmt.executeUpdate();

            // Obtém o ID gerado automaticamente para o relatório
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                relatorio.setId(rs.getInt(1)); // Atribui o ID ao objeto Relatorio
            }

            System.out.println("Relatório gerado com sucesso!");

        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }
    }

    // Método para buscar um relatório pelo ID
    public Relatorio buscarRelatorioPorId(int id) {
        // SQL para buscar um relatório com base no ID
        String sql = "SELECT * FROM Relatorio WHERE id = ?";
        Relatorio relatorio = null;

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura o parâmetro da query com o ID do relatório
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Verifica se o relatório foi encontrado e cria um objeto Relatorio com os dados retornados
            if (rs.next()) {
                relatorio = new Relatorio(
                        rs.getInt("id"), // ID do relatório
                        rs.getString("tipo_relatorio"), // Tipo de relatório
                        rs.getDate("data_geracao"), // Data de geração
                        rs.getString("dados_relatorio") // Dados do relatório
                );
            }

        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }

        // Retorna o relatório encontrado ou null caso não tenha sido encontrado
        return relatorio;
    }

    // Método para listar todos os relatórios de um tipo específico
    public List<Relatorio> listarRelatoriosPorTipo(String tipoRelatorio) {
        // SQL para buscar todos os relatórios de um tipo específico
        String sql = "SELECT * FROM Relatorio WHERE tipo_relatorio = ?";
        List<Relatorio> relatorios = new ArrayList<>();

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura o parâmetro da query com o tipo de relatório
            stmt.setString(1, tipoRelatorio);
            ResultSet rs = stmt.executeQuery();

            // Itera sobre os resultados e cria objetos Relatorio com os dados retornados
            while (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getInt("id"), // ID do relatório
                        rs.getString("tipo_relatorio"), // Tipo de relatório
                        rs.getDate("data_geracao"), // Data de geração
                        rs.getString("dados_relatorio") // Dados do relatório
                );
                // Adiciona o relatório à lista
                relatorios.add(relatorio);
            }

        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }

        // Retorna a lista de relatórios
        return relatorios;
    }

    // Método para gerar o boletim de um aluno
    public String gerarBoletim(int alunoId) {
        String boletim = "";
        // SQL para buscar as notas de um aluno em cada disciplina
        String sql = "SELECT Disciplina.nome, Nota.valor_nota " +
                     "FROM Nota " +
                     "JOIN Disciplina ON Nota.disciplina_id = Disciplina.id " +
                     "WHERE Nota.aluno_id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura o parâmetro da query com o ID do aluno
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            // Monta o boletim com as notas do aluno
            StringBuilder sb = new StringBuilder();
            sb.append("Boletim do Aluno ID: ").append(alunoId).append("\n");

            // Itera sobre os resultados e adiciona cada disciplina e nota ao boletim
            while (rs.next()) {
                sb.append("Disciplina: ").append(rs.getString("nome"))
                  .append(" - Nota: ").append(rs.getDouble("valor_nota"))
                  .append("\n");
            }

            // Converte o StringBuilder para uma String final
            boletim = sb.toString();
        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }

        // Retorna o boletim gerado
        return boletim;
    }

    // Método para gerar um relatório de frequência de um aluno
    public String gerarRelatorioFrequencia(int alunoId) {
        String frequencia = "";
        // SQL para buscar a frequência de um aluno em cada disciplina
        String sql = "SELECT Disciplina.nome, Frequencia.presenca, Frequencia.data " +
                     "FROM Frequencia " +
                     "JOIN Disciplina ON Frequencia.disciplina_id = Disciplina.id " +
                     "WHERE Frequencia.aluno_id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura o parâmetro da query com o ID do aluno
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            // Monta o relatório de frequência
            StringBuilder sb = new StringBuilder();
            sb.append("Relatório de Frequência do Aluno ID: ").append(alunoId).append("\n");

            // Itera sobre os resultados e adiciona cada disciplina, presença e data ao relatório
            while (rs.next()) {
                sb.append("Disciplina: ").append(rs.getString("nome"))
                  .append(" - Presença: ").append(rs.getBoolean("presenca") ? "Presente" : "Ausente")
                  .append(" - Data: ").append(rs.getDate("data"))
                  .append("\n");
            }

            // Converte o StringBuilder para uma String final
            frequencia = sb.toString();
        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }

        // Retorna o relatório de frequência gerado
        return frequencia;
    }
}
