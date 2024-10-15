package com.br.ezequielzz.Model.DAOs;

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

// Classe responsável por interagir com a tabela Disciplina no banco de dados
public class DisciplinaDAO {

    // Método para inserir uma nova disciplina no banco de dados
    public void criarDisciplina(Disciplina disciplina) {
        // Query SQL para inserir uma nova disciplina, utilizando nome e turma_id
        String sql = "INSERT INTO Disciplina (nome, turma_id) VALUES (?, ?)";

        // Conexão com o banco e execução da query usando try-with-resources (fecha automaticamente os recursos)
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os parâmetros da query com base nos atributos da disciplina
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getTurmaId());

            // Executa a query de inserção
            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente pelo banco de dados e define no objeto disciplina
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                disciplina.setId(rs.getInt(1)); // Pega o primeiro valor da chave gerada
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }
    }

    // Método para buscar disciplinas associadas a uma turma específica
    public List<Disciplina> buscarDisciplinasPorTurma(int turmaId) {
        // Query SQL para selecionar disciplinas de uma turma específica
        String sql = "SELECT d.* FROM Disciplina d WHERE d.turma_id = ?";
        List<Disciplina> disciplinas = new ArrayList<>(); // Lista para armazenar disciplinas

        // Conexão com o banco e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o parâmetro da query para o id da turma
            stmt.setInt(1, turmaId);
            ResultSet rs = stmt.executeQuery();

            // Itera sobre o resultado da query e adiciona disciplinas à lista
            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("id"),            // ID da disciplina
                        rs.getString("nome"),       // Nome da disciplina
                        rs.getInt("turma_id")       // ID da turma
                );
                disciplinas.add(disciplina); // Adiciona à lista
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }

        return disciplinas; // Retorna a lista de disciplinas
    }

    // Método para consultar as notas de um aluno em uma disciplina específica
    public List<Nota> consultarNotasAluno(Disciplina disciplina, Aluno aluno) {
        // Query SQL para selecionar as notas de um aluno em uma disciplina
        String sql = "SELECT * FROM Nota WHERE aluno_id = ? AND disciplina_id = ?";
        List<Nota> notas = new ArrayList<>(); // Lista para armazenar as notas

        // Conexão com o banco e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da query com os IDs do aluno e da disciplina
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, disciplina.getId());
            ResultSet rs = stmt.executeQuery();

            // Itera sobre o resultado da query e cria objetos Nota
            while (rs.next()) {
                Nota nota = new Nota(
                        rs.getInt("id"),            // ID da nota
                        rs.getInt("aluno_id"),      // ID do aluno
                        rs.getInt("disciplina_id"), // ID da disciplina
                        rs.getFloat("valor_nota"),  // Valor da nota
                        rs.getDate("data")          // Data da nota
                );
                notas.add(nota); // Adiciona a nota à lista
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }

        return notas; // Retorna a lista de notas
    }

    // Método para buscar uma disciplina pelo ID
    public Disciplina buscarDisciplinaPorId(int id) {
        // Query SQL para selecionar uma disciplina com base no ID
        String sql = "SELECT * FROM Disciplina WHERE id = ?";
        Disciplina disciplina = null; // Inicializa a variável que irá armazenar a disciplina

        // Conexão com o banco e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o parâmetro da query para o ID da disciplina
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Se houver resultado, cria uma nova Disciplina com os dados retornados
            if (rs.next()) {
                disciplina = new Disciplina(
                        rs.getInt("id"),            // ID da disciplina
                        rs.getString("nome"),       // Nome da disciplina
                        rs.getInt("turma_id")       // ID da turma
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro caso ocorra uma exceção
        }

        return disciplina; // Retorna a disciplina encontrada (ou null se não encontrada)
    }
}
