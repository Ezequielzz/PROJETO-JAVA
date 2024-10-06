package com.br.ezequielzz;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.br.ezequielzz.Model.Database.ConnectionFactory;
import com.br.ezequielzz.View.EscolaView;

public class App {

    public static void main(String[] args) {
        // Estabelece conexão com o banco de dados
        Connection connection = ConnectionFactory.getConnection();

        // Inicializa a interface gráfica
        SwingUtilities.invokeLater(() -> {
            EscolaView escolaView = new EscolaView();
            escolaView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            escolaView.setVisible(true);
        });
    }
}