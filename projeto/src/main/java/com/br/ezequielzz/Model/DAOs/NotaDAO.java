package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Database.ConnectionFactory;
import com.br.ezequielzz.Model.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

// Classe DAO responsável pelas operações CRUD relacionadas à entidade "Nota"
public class NotaDAO {

    // Método para registrar uma nova nota no banco de dados
    public void registrarNota(Nota nota) {
        // SQL de inserção de uma nova nota com aluno_id, disciplina_id, valor_nota e data
        String sql = "INSERT INTO Nota (aluno_id, disciplina_id, valor_nota, data) VALUES (?, ?, ?, ?)";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os valores dos parâmetros da query
            stmt.setInt(1, nota.getAlunoId()); // Define o ID do aluno
            stmt.setInt(2, nota.getDisciplinaId()); // Define o ID da disciplina
            stmt.setFloat(3, nota.getValorNota()); // Define o valor da nota
            stmt.setDate(4, new Date(nota.getData().getTime())); // Converte java.util.Date para java.sql.Date

            // Executa a query de inserção
            stmt.executeUpdate();

            // Recupera o ID gerado para a nota inserida
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                nota.setId(rs.getInt(1)); // Define o ID da nota gerada no objeto "Nota"
            }

            // Exibe uma mensagem de sucesso
            System.out.println("Nota registrada com sucesso!");

        } catch (SQLException e) {
            // Caso ocorra um erro de SQL, exibe a pilha de erro
            e.printStackTrace();
        }
    }

    // Método para atualizar os dados de uma nota existente
    public void atualizarNota(Nota nota) {
        // SQL para atualizar o valor da nota e a data com base no ID da nota
        String sql = "UPDATE Nota SET valor_nota = ?, data = ? WHERE id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os valores dos parâmetros da query
            stmt.setFloat(1, nota.getValorNota()); // Atualiza o valor da nota
            stmt.setDate(2, new Date(nota.getData().getTime())); // Atualiza a data da nota
            stmt.setInt(3, nota.getId()); // Define o ID da nota que será atualizada

            // Executa a query de atualização e verifica quantas linhas foram afetadas
            int rowsUpdated = stmt.executeUpdate();

            // Verifica se a nota foi atualizada com sucesso e exibe a mensagem apropriada
            if (rowsUpdated > 0) {
                System.out.println("Nota atualizada com sucesso!");
            } else {
                System.out.println("Falha ao atualizar a nota.");
            }

        } catch (SQLException e) {
            // Caso ocorra um erro de SQL, exibe a pilha de erro
            e.printStackTrace();
        }
    }

    // Método para buscar uma nota específica pelo seu ID
    public Nota buscarNotaPorId(int id) {
        // SQL para selecionar uma nota com base no ID
        String sql = "SELECT * FROM Nota WHERE id = ?";
        Nota nota = null;

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o valor do parâmetro na query (ID da nota)
            stmt.setInt(1, id);

            // Executa a query e armazena o resultado
            ResultSet rs = stmt.executeQuery();

            // Verifica se o resultado existe e preenche o objeto "Nota" com os dados da nota encontrada
            if (rs.next()) {
                nota = new Nota(
                        rs.getInt("id"), // ID da nota
                        rs.getInt("aluno_id"), // ID do aluno
                        rs.getInt("disciplina_id"), // ID da disciplina
                        rs.getFloat("valor_nota"), // Valor da nota
                        rs.getDate("data") // Data da nota
                );
            }

        } catch (SQLException e) {
            // Caso ocorra um erro de SQL, exibe a pilha de erro
            e.printStackTrace();
        }

        return nota; // Retorna o objeto "Nota" preenchido ou null se não encontrado
    }
}
