package com.br.ezequielzz.View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.RelatorioController;
import com.br.ezequielzz.Model.Aluno;

public class RelatorioPanel {
    private RelatorioController relatorioController;
    private AlunoController alunoController;

    public RelatorioPanel(RelatorioController relatorioController, AlunoController alunoController) {
        this.relatorioController = relatorioController;
        this.alunoController = alunoController;
    }
    
    public JPanel criarPainelGerarRelatorio() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Label e JComboBox para selecionar Aluno
        JLabel labelAluno = new JLabel("Selecione o Aluno:");
        JComboBox<Aluno> comboBoxAluno = new JComboBox<>();
        painel.add(labelAluno);
        painel.add(comboBoxAluno);

        // Botões para gerar diferentes tipos de relatório
        JButton botaoGerarBoletim = new JButton("Gerar Boletim");
        JButton botaoGerarFrequencia = new JButton("Gerar Relatório de Frequência");
        painel.add(botaoGerarBoletim);
        painel.add(botaoGerarFrequencia);

        // Preenchendo JComboBox com os alunos
        List<Aluno> alunos = alunoController.listarTodosAlunos();
        for (Aluno aluno : alunos) {
            comboBoxAluno.addItem(aluno);
        }

        // Ação do botão para gerar boletim
        botaoGerarBoletim.addActionListener(e -> {
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem();
            if (alunoSelecionado != null) {
                String caminhoArquivo = "F:\\Samuel SENAI\\Projeto-JAVA\\boletim_aluno"
                        + alunoSelecionado.getId() + ".txt"; // Defina o caminho
                relatorioController.gerarRelatorioBoletim(alunoSelecionado.getId(), caminhoArquivo);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno.");
            }
        });

        // Ação do botão para gerar relatório de frequência
        botaoGerarFrequencia.addActionListener(e -> {
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem();
            if (alunoSelecionado != null) {
                String caminhoArquivo = "F:\\Samuel SENAI\\PROJETO-JAVA\\frequencia_aluno"
                        + alunoSelecionado.getId() + ".txt"; // Defina o caminho
                relatorioController.gerarRelatorioFrequencia(alunoSelecionado.getId(), caminhoArquivo);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno.");
            }
        });
        return painel;
    }
}
