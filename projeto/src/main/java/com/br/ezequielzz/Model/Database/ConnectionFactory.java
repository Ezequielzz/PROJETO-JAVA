package com.br.ezequielzz.Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/gerenciamento_escola"; // URL BD
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Registra o driver JDBC
                Class.forName("org.postgresql.Driver");
                // Cria a conexão com o banco de dados
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
