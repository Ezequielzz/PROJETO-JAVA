package com.br.ezequielzz.View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Turma;

public class TurmaPanel {
    private TurmaController turmaController;

    public TurmaPanel(TurmaController turmaController) {
        this.turmaController = turmaController;
    }
    
    public JPanel criarPainelCriarTurma() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Label e campo de texto para série
        JLabel labelSerie = new JLabel("Série:");
        JTextField campoSerie = new JTextField(20);
        painel.add(labelSerie);
        painel.add(campoSerie);

        // Label e campo de texto para ano letivo
        JLabel labelAnoLetivo = new JLabel("Ano Letivo:");
        JTextField campoAnoLetivo = new JTextField(20);
        painel.add(labelAnoLetivo);
        painel.add(campoAnoLetivo);

        // Label e JComboBox para turno
        JLabel labelTurno = new JLabel("Turno:");
        JComboBox<String> comboTurno = new JComboBox<>(new String[] { "matutino", "vespertino", "noturno" });
        painel.add(labelTurno);
        painel.add(comboTurno);

        // Label e campo de texto para sala
        JLabel labelSala = new JLabel("Sala:");
        JTextField campoSala = new JTextField(20);
        painel.add(labelSala);
        painel.add(campoSala);

        // Botão para criar turma
        JButton botaoCriarTurma = new JButton("Criar Turma");
        painel.add(botaoCriarTurma);

        // Ação do botão para criar a turma
        botaoCriarTurma.addActionListener(e -> {
            String serie = campoSerie.getText();
            String anoLetivo = campoAnoLetivo.getText();
            String turno = (String) comboTurno.getSelectedItem();
            String sala = campoSala.getText();

            // Validação do ano letivo
            if (serie.isEmpty() || anoLetivo.isEmpty() || turno == null || sala.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            } else {
                try {
                    int ano = Integer.parseInt(anoLetivo); // Tenta converter o ano letivo para um inteiro
                    if (ano < 2024 || ano > 2027) { // Verifica se o ano está dentro de um intervalo válido
                        JOptionPane.showMessageDialog(null, "Ano letivo inválido. Deve estar entre 2024 e 2027.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ano letivo deve ser um número válido.");
                    return;
                }

                Turma turma = new Turma(0, serie, anoLetivo, turno, sala);
                turmaController.criarTurma(turma);
                JOptionPane.showMessageDialog(null, "Turma criada com sucesso!");
            }
        });

        return painel;
    }
}
