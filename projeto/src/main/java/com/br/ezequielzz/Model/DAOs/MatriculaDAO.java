package com.br.ezequielzz.Model.DAOs;

import com.br.ezequielzz.Model.Matricula;
import com.br.ezequielzz.Model.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

// Classe responsável pelas operações de matrícula no banco de dados
public class MatriculaDAO {

    // Método para realizar uma nova matrícula de um aluno em uma turma
    public void realizarMatricula(Matricula matricula) {
        // SQL para inserir uma nova matrícula com aluno_id, turma_id, data_matricula e status
        String sql = "INSERT INTO Matricula (aluno_id, turma_id, data_matricula, status) VALUES (?, ?, ?, ?)";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definindo os valores dos parâmetros na query
            stmt.setInt(1, matricula.getAlunoId()); // Define o ID do aluno
            stmt.setInt(2, matricula.getTurmaId()); // Define o ID da turma
            stmt.setDate(3, new Date(matricula.getDataMatricula().getTime())); // Converte java.util.Date para java.sql.Date
            stmt.setString(4, matricula.getStatus()); // Define o status da matrícula (ex: "ativo", "cancelado")

            // Executa a query de inserção
            stmt.executeUpdate();

            // Exibe uma mensagem de sucesso caso a matrícula tenha sido realizada com sucesso
            System.out.println("Matrícula realizada com sucesso!");

        } catch (SQLException e) {
            // Caso ocorra um erro de SQL, exibe a pilha de erro
            e.printStackTrace();
        }
    }

    // Método para cancelar a matrícula de um aluno com base no ID do aluno
    public void cancelarMatricula(int alunoId) {
        // SQL para atualizar o status da matrícula para "cancelado", baseado no aluno_id
        String sql = "UPDATE Matricula SET status = ? WHERE aluno_id = ?";

        // Tentativa de conexão com o banco de dados e execução da query
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os valores dos parâmetros da query
            stmt.setString(1, "cancelado"); // Define o status como "cancelado"
            stmt.setInt(2, alunoId); // Define o ID do aluno para realizar a alteração

            // Executa a query de atualização
            int rowsUpdated = stmt.executeUpdate();

            // Verifica se alguma linha foi atualizada e exibe a mensagem apropriada
            if (rowsUpdated > 0) {
                System.out.println("Matrícula cancelada com sucesso!");
            } else {
                System.out.println("Falha ao cancelar a matrícula.");
            }

        } catch (SQLException e) {
            // Caso ocorra um erro de SQL, exibe a pilha de erro
            e.printStackTrace();
        }
    }
}
