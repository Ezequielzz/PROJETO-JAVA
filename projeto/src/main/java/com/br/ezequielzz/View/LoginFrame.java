package com.br.ezequielzz.View;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Tela de Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo de usuário
        panel.add(new JLabel("Usuário:"));
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(200, 25));
        usernameField.setMinimumSize(new Dimension(200, 25));
        panel.add(usernameField);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campo de senha
        panel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25)); // Definindo tamanho fixo
        panel.add(passwordField);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão de login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            JOptionPane.showMessageDialog(null, "Login realizado por: " + username);
        });
        panel.add(loginButton);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
