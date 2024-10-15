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

// Classe responsável pela interface gráfica para gerar relatórios
public class RelatorioPanel {
    // Controladores necessários para gerenciar relatórios e alunos
    private RelatorioController relatorioController;
    private AlunoController alunoController;

    // Construtor da classe RelatorioPanel que recebe os controladores como parâmetros
    public RelatorioPanel(RelatorioController relatorioController, AlunoController alunoController) {
        this.relatorioController = relatorioController; // Inicializa o controlador de relatórios
        this.alunoController = alunoController; // Inicializa o controlador de alunos
    }

    // Método que cria e retorna um painel para gerar relatórios
    public JPanel criarPainelGerarRelatorio() {
        // Criação do painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Define o layout do painel como vertical

        // Label e JComboBox para selecionar Aluno
        JLabel labelAluno = new JLabel("Selecione o Aluno:"); // Rótulo para seleção de aluno
        JComboBox<Aluno> comboBoxAluno = new JComboBox<>(); // ComboBox para listar os alunos
        painel.add(labelAluno); // Adiciona o rótulo ao painel
        painel.add(comboBoxAluno); // Adiciona a ComboBox ao painel

        // Botões para gerar diferentes tipos de relatório
        JButton botaoGerarBoletim = new JButton("Gerar Boletim"); // Botão para gerar boletim
        JButton botaoGerarFrequencia = new JButton("Gerar Relatório de Frequência"); // Botão para gerar relatório de frequência
        painel.add(botaoGerarBoletim); // Adiciona o botão de gerar boletim ao painel
        painel.add(botaoGerarFrequencia); // Adiciona o botão de gerar relatório de frequência ao painel

        // Preenchendo JComboBox com os alunos
        List<Aluno> alunos = alunoController.listarTodosAlunos(); // Obtém a lista de alunos do controlador
        for (Aluno aluno : alunos) { // Itera sobre os alunos
            comboBoxAluno.addItem(aluno); // Adiciona cada aluno ao ComboBox
        }

        // Ação do botão para gerar boletim
        botaoGerarBoletim.addActionListener(e -> { // Adiciona um ouvinte de ação ao botão
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem(); // Obtém o aluno selecionado
            if (alunoSelecionado != null) { // Verifica se um aluno foi selecionado
                String caminhoArquivo = "F:\\Samuel SENAI\\Projeto-JAVA\\boletim_aluno"
                        + alunoSelecionado.getId() + ".txt"; // Define o caminho do arquivo do boletim
                relatorioController.gerarRelatorioBoletim(alunoSelecionado.getId(), caminhoArquivo); // Gera o relatório de boletim
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno."); // Mensagem de erro se nenhum aluno for selecionado
            }
        });

        // Ação do botão para gerar relatório de frequência
        botaoGerarFrequencia.addActionListener(e -> { // Adiciona um ouvinte de ação ao botão
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem(); // Obtém o aluno selecionado
            if (alunoSelecionado != null) { // Verifica se um aluno foi selecionado
                String caminhoArquivo = "F:\\Samuel SENAI\\PROJETO-JAVA\\frequencia_aluno"
                        + alunoSelecionado.getId() + ".txt"; // Define o caminho do arquivo do relatório de frequência
                relatorioController.gerarRelatorioFrequencia(alunoSelecionado.getId(), caminhoArquivo); // Gera o relatório de frequência
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno."); // Mensagem de erro se nenhum aluno for selecionado
            }
        });

        return painel; // Retorna o painel criado
    }
}
