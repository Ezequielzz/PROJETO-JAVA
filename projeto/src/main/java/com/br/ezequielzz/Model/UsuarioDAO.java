//package com.br.ezequielzz.Model;
//
//import com.br.ezequielzz.Model.Database.ConnectionFactory;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class UsuarioDAO {
//    private Connection connection;
//
//    public UsuarioDAO() {
//        this.connection = ConnectionFactory.getConnection();
//    }
//
//    public void adicionarUsuario(Pessoa usuario) {
//        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
//
//        try {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, usuario.getNome());
//            stmt.setString(2, usuario.getEmail());
//
//            stmt.execute();
//            stmt.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
