package com.br.ezequielzz.View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.ProfessorController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Professor;
import com.br.ezequielzz.Model.Turma;

public class DisciplinaPanel {
    private TurmaController turmaController;
    private DisciplinaController disciplinaController;
    private ProfessorController professorController;

    public DisciplinaPanel(TurmaController turmaController, DisciplinaController disciplinaController, ProfessorController professorController) {
        this.turmaController = turmaController;
        this.disciplinaController = disciplinaController;
        this.professorController = professorController;
    }
    
    public JPanel criarPainelCriarDisciplina() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Label e campo de texto para nome da disciplina
        JLabel labelNome = new JLabel("Nome da Disciplina:");
        JTextField campoNome = new JTextField(20);
        painel.add(labelNome);
        painel.add(campoNome);

        // Label e JComboBox para selecionar a turma
        JLabel labelTurma = new JLabel("Selecione a Turma:");
        JComboBox<Turma> comboBoxTurma = new JComboBox<>();
        painel.add(labelTurma);
        painel.add(comboBoxTurma);

        // Label e JComboBox para selecionar o professor
        JLabel labelProfessor = new JLabel("Selecione o Professor:");
        JComboBox<Professor> comboBoxProfessor = new JComboBox<>();
        painel.add(labelProfessor);
        painel.add(comboBoxProfessor);

        // Botão para criar disciplina
        JButton botaoCriarDisciplina = new JButton("Criar Disciplina");
        painel.add(botaoCriarDisciplina);

        // Preenchendo JComboBox com as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas();
        for (Turma turma : turmas) {
            comboBoxTurma.addItem(turma);
        }

        // Preenchendo JComboBox com os professores do banco de dados
        List<Professor> professores = professorController.listarTodosProfessores();
        for (Professor professor : professores) {
            comboBoxProfessor.addItem(professor);
        }

        // Ação do botão para criar a disciplina
        botaoCriarDisciplina.addActionListener(e -> {
            String nomeDisciplina = campoNome.getText();
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem();
            if (nomeDisciplina.isEmpty() || turmaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            } else {
                Disciplina disciplina = new Disciplina(0, nomeDisciplina, turmaSelecionada.getTurmaId());
                disciplinaController.criarDisciplina(disciplina);
                JOptionPane.showMessageDialog(null, "Disciplina criada com sucesso!");
            }
            
        });

        return painel;
    }
}
