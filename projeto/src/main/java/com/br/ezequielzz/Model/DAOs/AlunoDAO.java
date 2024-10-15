package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

// Classe responsável pelo acesso aos dados relacionados ao Aluno no banco de dados
public class AlunoDAO {

    // Método para inserir um novo aluno no banco de dados
    public void criarAluno(Aluno aluno) throws SQLException {
        // Query SQL para inserir um novo aluno com os campos correspondentes
        String sql = "INSERT INTO aluno (nome, cpf, data_nascimento, endereco, telefone, senha, turma_id, status_matricula) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Utilizando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados após o uso
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Definindo os valores dos parâmetros da query usando os atributos do objeto aluno
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, new Date(aluno.getDataNascimento().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setString(4, aluno.getEndereco());
            stmt.setString(5, aluno.getTelefone());
            stmt.setString(6, aluno.getSenha());
            stmt.setInt(7, aluno.getTurmaId());
            stmt.setString(8, aluno.getStatusMatricula());

            // Executa a query de inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace e lança uma exceção com mensagem específica
            e.printStackTrace();
            throw new SQLException("Erro ao inserir aluno", e);
        }
    }

    // Método para listar todos os alunos cadastrados no banco de dados
    public List<Aluno> listarTodos() {
        // Lista para armazenar os alunos encontrados
        List<Aluno> alunos = new ArrayList<>();
        // Query SQL para selecionar todos os registros da tabela 'aluno'
        String sql = "SELECT * FROM aluno";

        // Conexão com o banco de dados e execução da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados da query
            while (rs.next()) {
                // Cria um novo objeto Aluno e preenche os atributos com os dados do ResultSet
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("senha"),
                        rs.getInt("turma_id"),
                        rs.getString("status_matricula"));
                // Adiciona o aluno à lista
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }

        // Retorna a lista de alunos
        return alunos;
    }

    // Método para listar alunos por uma turma específica
    public List<Aluno> listarAlunosPorTurma(int turmaId) {
        // Lista para armazenar os alunos da turma específica
        List<Aluno> alunos = new ArrayList<>();
        // Query SQL para selecionar alunos de uma determinada turma
        String sql = "SELECT * FROM Aluno WHERE turma_id = ?";

        // Conexão com o banco de dados e preparação da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o parâmetro 'turma_id' da query
            stmt.setInt(1, turmaId);
            ResultSet rs = stmt.executeQuery();

            // Itera sobre os resultados da query
            while (rs.next()) {
                // Cria um novo objeto Aluno e preenche os atributos com os dados do ResultSet
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("senha"),
                        rs.getInt("turma_id"),
                        rs.getString("status_matricula")
                );
                // Adiciona o aluno à lista
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }

        // Retorna a lista de alunos da turma
        return alunos;
    }

    // Método para excluir um aluno do banco de dados pelo ID
    public void excluirAluno(int alunoId) throws SQLException {
        // Query SQL para excluir um aluno com base no ID
        String sql = "DELETE FROM aluno WHERE id = ?";

        // Conexão com o banco de dados e preparação da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define o parâmetro 'id' da query
            stmt.setInt(1, alunoId);
            // Executa a query de exclusão e verifica o número de linhas afetadas
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                // Se nenhuma linha foi afetada, significa que o ID não foi encontrado
                throw new SQLException("Nenhum aluno encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace e lança uma exceção com mensagem específica
            e.printStackTrace();
            throw new SQLException("Erro ao excluir aluno", e);
        }
    }

    // Método para atualizar as informações de um aluno no banco de dados
    public void atualizarAluno(Aluno aluno) throws SQLException {
        // Query SQL para atualizar os dados do aluno com base no ID
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, data_nascimento = ?, endereco = ?, telefone = ?, senha = ?, turma_id = ?, status_matricula = ? WHERE id = ?";

        // Conexão com o banco de dados e preparação da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define os parâmetros da query com os dados do objeto aluno
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, new Date(aluno.getDataNascimento().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setString(4, aluno.getEndereco());
            stmt.setString(5, aluno.getTelefone());
            stmt.setString(6, aluno.getSenha());
            stmt.setInt(7, aluno.getTurmaId());
            stmt.setString(8, aluno.getStatusMatricula());
            stmt.setInt(9, aluno.getId());

            // Executa a query de atualização e verifica o número de linhas afetadas
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                // Se nenhuma linha foi afetada, significa que o ID não foi encontrado
                throw new SQLException("Nenhum aluno encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace e lança uma exceção com mensagem específica
            e.printStackTrace();
            throw new SQLException("Erro ao atualizar aluno", e);
        }
    }

    // Método para buscar um aluno no banco de dados pelo ID
    public Aluno buscarAlunoPorId(int id) throws SQLException {
        // Query SQL para selecionar um aluno com base no ID
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Aluno aluno = null; // Inicializa a variável que irá armazenar o aluno encontrado
    
        // Conexão com o banco de dados e preparação da query
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            // Define o parâmetro 'id' da query
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            // Se houver resultado, preenche o objeto Aluno com os dados retornados
            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("data_nascimento"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setTurmaId(rs.getInt("turma_id"));
                aluno.setStatusMatricula(rs.getString("status_matricula"));
            }
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace e lança uma exceção com mensagem específica
            e.printStackTrace();
            throw new SQLException("Erro ao buscar aluno por ID", e);
        }
    
        // Retorna o aluno encontrado (ou null se não encontrado)
        return aluno;
    }
}
