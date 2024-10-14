package com.br.ezequielzz.View;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.MatriculaController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Matricula;
import com.br.ezequielzz.Model.Turma;

public class MatriculaPanel {

    private MatriculaController matriculaController; // Controlador para gerenciar matrículas
    private AlunoController alunoController; // Controlador para gerenciar alunos
    private TurmaController turmaController; // Controlador para gerenciar turmas

    // Construtor da classe MatriculaPanel, inicializa os controladores
    public MatriculaPanel(MatriculaController matriculaController, AlunoController alunoController, TurmaController turmaController) {
        this.matriculaController = matriculaController; // Atribui o controlador de matrícula
        this.alunoController = alunoController; // Atribui o controlador de aluno
        this.turmaController = turmaController; // Atribui o controlador de turma
    }

    // Método para criar o painel de matrícula
    public JPanel criarMatriculaPanel() {
        JPanel panel = new JPanel(); // Cria um novo painel
        panel.setLayout(new GridLayout(5, 2)); // Define o layout do painel como uma grade de 5 linhas e 2 colunas

        // Label e JComboBox para selecionar o aluno
        JLabel alunoLabel = new JLabel("Aluno:"); // Rótulo para o aluno
        JComboBox<String> alunoComboBox = new JComboBox<>(); // ComboBox para seleção de alunos
        loadAlunos(alunoComboBox); // Carregar os alunos no ComboBox

        // Label e JComboBox para selecionar a turma
        JLabel turmaLabel = new JLabel("Turma:"); // Rótulo para a turma
        JComboBox<String> turmaComboBox = new JComboBox<>(); // ComboBox para seleção de turmas
        loadTurmas(turmaComboBox); // Carregar as turmas no ComboBox

        // Label e campo para data de matrícula
        JLabel dataMatriculaLabel = new JLabel("Data de Matrícula:"); // Rótulo para a data de matrícula
        JTextField dataMatriculaField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date())); // Campo para a data de matrícula, pré-preenchido com a data atual

        // Label e JComboBox para status da matrícula
        JLabel statusLabel = new JLabel("Status:"); // Rótulo para o status da matrícula
        JComboBox<String> statusComboBox = new JComboBox<>(new String[] { "matriculado" }); // ComboBox para status da matrícula, inicia com "matriculado"

        // Botão para efetuar a matrícula
        JButton matricularButton = new JButton("Realizar Matrícula"); // Botão para realizar a matrícula
        matricularButton.addActionListener(new ActionListener() { // Adiciona um listener de ação ao botão
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obter o aluno e turma selecionados
                    String alunoSelecionado = (String) alunoComboBox.getSelectedItem(); // Aluno selecionado no ComboBox
                    String turmaSelecionada = (String) turmaComboBox.getSelectedItem(); // Turma selecionada no ComboBox
                    if (alunoSelecionado == null || turmaSelecionada == null) { // Verifica se um aluno e uma turma foram selecionados
                        JOptionPane.showMessageDialog(null, "Selecione um aluno e uma turma."); // Mensagem de erro
                        return; // Sai do método se não houver seleção válida
                    }

                    // Extrair os IDs do aluno e da turma selecionados
                    int alunoId = Integer.parseInt(alunoSelecionado.split("-")[0].trim()); // Obtém o ID do aluno
                    int turmaId = Integer.parseInt(turmaSelecionada.split("-")[0].trim()); // Obtém o ID da turma

                    // Obter a data de matrícula e o status
                    String dataMatriculaStr = dataMatriculaField.getText().trim(); // Obtém a data de matrícula do campo de texto
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
                    java.util.Date dataMatricula = formatter.parse(dataMatriculaStr); // Converte a string da data em um objeto Date
                    String status = (String) statusComboBox.getSelectedItem(); // Obtém o status selecionado

                    // Criar o objeto Matricula
                    Matricula matricula = new Matricula(0, alunoId, turmaId, dataMatricula, status); // Cria um novo objeto de matrícula

                    // Chamar o controller para realizar a matrícula
                    matriculaController.realizarMatricula(matricula); // Chama o método do controlador para realizar a matrícula

                    JOptionPane.showMessageDialog(null, "Matrícula realizada com sucesso!"); // Mensagem de sucesso
                } catch (Exception ex) {
                    // Mensagem de erro em caso de exceção
                    JOptionPane.showMessageDialog(null, "Erro ao realizar matrícula: " + ex.getMessage()); // Exibe a mensagem de erro
                }
            }
        });

        // Adicionar os componentes ao painel
        panel.add(alunoLabel); // Adiciona o rótulo do aluno
        panel.add(alunoComboBox); // Adiciona o ComboBox do aluno

        panel.add(turmaLabel); // Adiciona o rótulo da turma
        panel.add(turmaComboBox); // Adiciona o ComboBox da turma

        panel.add(dataMatriculaLabel); // Adiciona o rótulo da data de matrícula
        panel.add(dataMatriculaField); // Adiciona o campo de data de matrícula

        panel.add(statusLabel); // Adiciona o rótulo do status
        panel.add(statusComboBox); // Adiciona o ComboBox do status

        panel.add(new JLabel()); // Espaçamento
        panel.add(matricularButton); // Adiciona o botão de realizar matrícula

        return panel; // Retorna o painel criado
    }

    // Método para carregar as turmas no JComboBox
    private void loadTurmas(JComboBox<String> turmaComboBox) {
        // Exemplo de como carregar as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas(); // Método que deve retornar a lista de turmas
        for (Turma turma : turmas) { // Percorre cada turma retornada
            turmaComboBox.addItem(turma.getTurmaId() + " - " + turma.getSerie()); // Exibe o ID e a série da turma
        }
    }

    // Método para carregar os alunos no JComboBox
    private void loadAlunos(JComboBox<String> alunoComboBox) {
        List<Aluno> alunos = alunoController.listarTodosAlunos(); // Lista todos os alunos
        for (Aluno aluno : alunos) { // Percorre cada aluno retornado
            alunoComboBox.addItem(aluno.getId() + " - " + aluno.getNome()); // Adiciona o ID e o nome do aluno ao ComboBox
        }
    }
}
