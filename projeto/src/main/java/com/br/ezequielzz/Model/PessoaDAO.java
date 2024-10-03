package com.br.ezequielzz.Model;

import com.br.ezequielzz.Model.Database.ConnectionFactory;
import com.br.ezequielzz.Model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDAO {

    // Método para criar uma nova Pessoa no banco de dados
    public int criarPessoa(Pessoa pessoa) {
        int pessoaId = -1;  // ID retornado após a criação

        String sql = "INSERT INTO Pessoa (nome, cpf, dataNascimento, endereco, telefone, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os parâmetros da consulta
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));  // Converte Date para SQL Date
            stmt.setString(4, pessoa.getEndereco());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getSenha());

            // Executa a inserção
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Recupera o ID gerado para a Pessoa
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pessoaId = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoaId;  // Retorna o ID gerado
    }
}
