package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Professor;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// Classe DAO responsável pelas operações CRUD relacionadas à entidade "Professor"
public class ProfessorDAO {

    // Método para criar um novo professor no banco de dados
    public void criarProfessor(Professor professor) {
        // SQL para inserir um novo professor com nome, CPF, data de nascimento, endereço, telefone e senha
        String sql = "INSERT INTO Professor (nome, cpf, data_nascimento, endereco, telefone, senha) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura os parâmetros da query com os dados do professor
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setDate(3, new Date(professor.getDataNascimento().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setString(4, professor.getEndereco());
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getSenha());

            // Executa a inserção no banco de dados
            stmt.executeUpdate();
            System.out.println("Professor criado com sucesso!");

        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }
    }

    // Método para listar todos os professores cadastrados no banco de dados
    public List<Professor> listarTodos() {
        // Lista para armazenar os professores
        List<Professor> professores = new ArrayList<>();
        // SQL para selecionar todos os professores
        String sql = "SELECT * FROM professor";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados e cria objetos Professor com os dados retornados
            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("id"), // ID do professor
                        rs.getString("nome"), // Nome do professor
                        rs.getString("cpf"), // CPF do professor
                        rs.getDate("data_nascimento"), // Data de nascimento
                        rs.getString("endereco"), // Endereço do professor
                        rs.getString("telefone"), // Telefone do professor
                        rs.getString("senha") // Senha do professor
                );
                // Adiciona o professor à lista
                professores.add(professor);
            }
        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }

        // Retorna a lista de professores
        return professores;
    }

    // Método para atribuir uma disciplina a um professor
    public void atribuirDisciplina(Professor professor, Disciplina disciplina) {
        // SQL para inserir uma relação entre professor e disciplina
        String sql = "INSERT INTO ProfessorDisciplina (professor_id, disciplina_id) VALUES (?, ?)";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura os parâmetros da query com o ID do professor e da disciplina
            stmt.setInt(1, professor.getId());
            stmt.setInt(2, disciplina.getId());

            // Executa a inserção no banco de dados
            stmt.executeUpdate();
            System.out.println("Disciplina atribuída com sucesso!");

        } catch (SQLException e) {
            // Exibe o erro em caso de falha
            e.printStackTrace();
        }
    }

    // Método para excluir um professor com base no ID fornecido
    public void excluirProfessor(int professorId) throws SQLException {
        // SQL para deletar um professor com base no ID
        String sql = "DELETE FROM professor WHERE id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Configura o parâmetro da query com o ID do professor
            stmt.setInt(1, professorId);

            // Executa a exclusão e verifica se alguma linha foi afetada
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum professor encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            // Exibe o erro e lança uma exceção caso a exclusão falhe
            e.printStackTrace();
            throw new SQLException("Erro ao excluir professor", e);
        }
    }

    // Método para atualizar os dados de um professor existente
    public void atualizarProfessor(Professor professor) throws SQLException {
        // SQL para atualizar os dados do professor com base no ID
        String sql = "UPDATE professor SET nome = ?, cpf = ?, data_nascimento = ?, endereco = ?, telefone = ?, senha = ? WHERE id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Configura os parâmetros da query com os novos dados do professor
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setDate(3, new Date(professor.getDataNascimento().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setString(4, professor.getEndereco());
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getSenha());
            stmt.setInt(7, professor.getId());

            // Executa a atualização e verifica se alguma linha foi afetada
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum professor encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            // Exibe o erro e lança uma exceção caso a atualização falhe
            e.printStackTrace();
            throw new SQLException("Erro ao atualizar professor", e);
        }
    }

    // Método para buscar um professor por ID
    public Professor buscarProfessorPorId(int id) throws SQLException {
        // SQL para selecionar um professor com base no ID
        String sql = "SELECT * FROM professor WHERE id = ?";
        Professor professor = null;

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Configura o parâmetro da query com o ID do professor
            stmt.setInt(1, id);

            // Executa a query e armazena o resultado
            ResultSet rs = stmt.executeQuery();

            // Verifica se o professor foi encontrado e preenche o objeto Professor com os dados
            if (rs.next()) {
                professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setDataNascimento(rs.getDate("data_nascimento"));
                professor.setEndereco(rs.getString("endereco"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            // Exibe o erro e lança uma exceção caso a busca falhe
            e.printStackTrace();
            throw new SQLException("Erro ao buscar professor por ID", e);
        }

        // Retorna o professor encontrado ou null caso não tenha sido encontrado
        return professor;
    }
}
