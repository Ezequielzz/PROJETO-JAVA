package com.br.ezequielzz.View;

import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.NotaController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Turma;

public class NotaPanel {
    private TurmaController turmaController;
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;
    private NotaController notaController;

    public NotaPanel(TurmaController turmaController, AlunoController alunoController,
            DisciplinaController disciplinaController, NotaController notaController) {
        this.turmaController = turmaController;
        this.alunoController = alunoController;
        this.disciplinaController = disciplinaController;
        this.notaController = notaController;
    }

    public JPanel criarPainelAtribuirNota() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Label e JComboBox para selecionar Turma
        JLabel labelTurma = new JLabel("Selecione a Turma:");
        JComboBox<Turma> comboBoxTurma = new JComboBox<>();
        painel.add(labelTurma);
        painel.add(comboBoxTurma);

        // Label e JComboBox para selecionar Aluno
        JLabel labelAluno = new JLabel("Selecione o Aluno:");
        JComboBox<Aluno> comboBoxAluno = new JComboBox<>();
        painel.add(labelAluno);
        painel.add(comboBoxAluno);

        // Label para exibir Disciplina
        JLabel labelDisciplina = new JLabel("Disciplina:");
        JComboBox<Disciplina> comboBoxDisciplina = new JComboBox<>();
        painel.add(labelDisciplina);
        painel.add(comboBoxDisciplina);

        // Label e campo de input para a Nota
        JLabel labelNota = new JLabel("Informe a Nota:");
        JTextField campoNota = new JTextField(5);
        painel.add(labelNota);
        painel.add(campoNota);

        // Botão para lançar a nota
        JButton botaoLancarNota = new JButton("Lançar Nota");
        painel.add(botaoLancarNota);

        // Preenchendo o JComboBox de turmas com as turmas do BD
        List<Turma> turmas = turmaController.listarTodasTurmas();
        for (Turma turma : turmas) {
            comboBoxTurma.addItem(turma);
        }

        // Listener para atualizar alunos e disciplina quando uma turma for selecionada
        comboBoxTurma.addActionListener(e -> {
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem();
            if (turmaSelecionada != null) {
                // Atualiza a lista de alunos da turma
                comboBoxAluno.removeAllItems();
                List<Aluno> alunos = alunoController.listarAlunosPorTurma(turmaSelecionada.getTurmaId());
                for (Aluno aluno : alunos) {
                    comboBoxAluno.addItem(aluno);
                }
                // Atualiza a lista de disciplinas da turma
                comboBoxDisciplina.removeAllItems();
                List<Disciplina> disciplinas = disciplinaController
                        .buscarDisciplinasPorTurma(turmaSelecionada.getTurmaId());
                for (Disciplina disciplina : disciplinas) {
                    comboBoxDisciplina.addItem(disciplina);
                }
            }
        });

        // Ação do botão de lançar nota
        botaoLancarNota.addActionListener(e -> {
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem();
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem();
            Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplina.getSelectedItem();

            if (alunoSelecionado != null && turmaSelecionada != null && disciplinaSelecionada != null) {
                try {
                    float nota = Float.parseFloat(campoNota.getText());
                    // Verificar se a nota está dentro do intervalo correto
                    if (nota < 0 || nota > 10) {
                        JOptionPane.showMessageDialog(null, "A nota deve estar entre 0 e 10.");
                        return; // Sai do método se a nota for inválida
                    }
                    // Registrar nota no banco de dados
                    notaController.registrarNota(alunoSelecionado.getId(), disciplinaSelecionada.getId(), nota,
                            new Date());
                    JOptionPane.showMessageDialog(null, "Nota lançada com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Informe uma nota válida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione aluno, turma e verifique a disciplina.");
            }
        });

        return painel;
    }
}
