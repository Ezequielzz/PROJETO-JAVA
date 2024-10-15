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

// Classe responsável por operações de CRUD relacionadas à tabela Frequencia no banco de dados
public class FrequenciaDAO {

    // Método para registrar uma nova frequência no banco de dados
    public void registrarFrequencia(Frequencia frequencia) {
        // SQL para inserir uma nova frequência com aluno_id, disciplina_id, data e presença
        String sql = "INSERT INTO Frequencia (aluno_id, disciplina_id, data, presenca) VALUES (?, ?, ?, ?)";

        // Conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Definindo os valores dos parâmetros na query
            stmt.setInt(1, frequencia.getAlunoId());
            stmt.setInt(2, frequencia.getDisciplinaId());
            stmt.setDate(3, new java.sql.Date(frequencia.getData().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setBoolean(4, frequencia.isPresenca());

            // Executa a query de inserção
            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente pelo banco de dados e define no objeto frequencia
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                frequencia.setId(rs.getInt(1)); // Pega o primeiro valor da chave gerada
            }

            System.out.println("Frequência registrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }
    }

    // Método para atualizar uma frequência existente no banco de dados
    public void atualizarFrequencia(Frequencia frequencia) {
        // SQL para atualizar os campos presença e data de uma frequência, com base no ID
        String sql = "UPDATE Frequencia SET presenca = ?, data = ? WHERE id = ?";

        // Conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definindo os valores dos parâmetros na query
            stmt.setBoolean(1, frequencia.isPresenca());
            stmt.setDate(2, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setInt(3, frequencia.getId());

            // Executa a query de atualização e verifica se alguma linha foi modificada
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Frequência atualizada com sucesso!");
            } else {
                System.out.println("Falha ao atualizar a frequência.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }
    }

    // Método para buscar uma frequência pelo seu ID
    public Frequencia buscarFrequenciaPorId(int id) {
        // SQL para selecionar uma frequência com base no ID
        String sql = "SELECT * FROM Frequencia WHERE id = ?";
        Frequencia frequencia = null; // Inicializa a variável que irá armazenar a frequência

        // Conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o valor do parâmetro ID
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Se houver resultado, cria uma nova instância de Frequencia com os dados retornados
            if (rs.next()) {
                frequencia = new Frequencia(
                        rs.getInt("id"),            // ID da frequência
                        rs.getInt("aluno_id"),      // ID do aluno
                        rs.getInt("disciplina_id"), // ID da disciplina
                        rs.getDate("data"),         // Data da frequência
                        rs.getBoolean("presenca")   // Presença (true/false)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }

        return frequencia; // Retorna a frequência encontrada (ou null se não encontrada)
    }

    // Método para consultar frequências de um aluno em uma disciplina entre duas datas
    public List<Frequencia> consultarFrequencia(int alunoId, int disciplinaId, Date dataInicial, Date dataFinal) {
        // SQL para selecionar frequências com base em aluno_id, disciplina_id e um intervalo de datas
        String sql = "SELECT * FROM Frequencia WHERE aluno_id = ? AND disciplina_id = ? AND data BETWEEN ? AND ?";
        List<Frequencia> frequencias = new ArrayList<>(); // Lista para armazenar as frequências

        // Conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os valores dos parâmetros na query
            stmt.setInt(1, alunoId);
            stmt.setInt(2, disciplinaId);
            stmt.setDate(3, new java.sql.Date(dataInicial.getTime())); // Data inicial
            stmt.setDate(4, new java.sql.Date(dataFinal.getTime()));   // Data final

            // Executa a query e processa os resultados
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Frequencia frequencia = new Frequencia(
                        rs.getInt("id"),            // ID da frequência
                        rs.getInt("aluno_id"),      // ID do aluno
                        rs.getInt("disciplina_id"), // ID da disciplina
                        rs.getDate("data"),         // Data da frequência
                        rs.getBoolean("presenca")   // Presença (true/false)
                );
                frequencias.add(frequencia); // Adiciona a frequência à lista
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }

        return frequencias; // Retorna a lista de frequências
    }
}
