package com.br.ezequielzz.View;

import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.FrequenciaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Frequencia;
import com.br.ezequielzz.Model.Turma;

public class FrequenciaPanel {
    private TurmaController turmaController;
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;
    private FrequenciaController frequenciaController;

    public FrequenciaPanel(TurmaController turmaController, AlunoController alunoController,
            DisciplinaController disciplinaController, FrequenciaController frequenciaController) {
        this.turmaController = turmaController;
        this.alunoController = alunoController;
        this.disciplinaController = disciplinaController;
        this.frequenciaController = frequenciaController;
    }

    public JPanel criarPainelRegistrarFrequencia() {
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

        // Label e JComboBox para selecionar presença
        JLabel labelPresenca = new JLabel("Presença:");
        JComboBox<String> comboBoxPresenca = new JComboBox<>(new String[] { "Presente", "Ausente" });
        painel.add(labelPresenca);
        painel.add(comboBoxPresenca);

        // Botão para registrar a presença
        JButton botaoRegistrarFrequencia = new JButton("Registrar Frequência");
        painel.add(botaoRegistrarFrequencia);

        // Preenchendo JComboBox de turmas com as turmas do BD
        List<Turma> turmas = turmaController.listarTodasTurmas();
        for (Turma turma : turmas) {
            comboBoxTurma.addItem(turma);
        }

        // Listener para atualizar alunos e disciplinas ao selecionar uma turma
        comboBoxTurma.addActionListener(e -> {
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem();
            if (turmaSelecionada != null) {
                // Atualizar lista de alunos
                comboBoxAluno.removeAllItems();
                List<Aluno> alunos = alunoController.listarAlunosPorTurma(turmaSelecionada.getTurmaId());
                for (Aluno aluno : alunos) {
                    comboBoxAluno.addItem(aluno);
                }

                // Atualizar lista de disciplinas
                comboBoxDisciplina.removeAllItems();
                List<Disciplina> disciplinas = disciplinaController
                        .buscarDisciplinasPorTurma(turmaSelecionada.getTurmaId());
                for (Disciplina disciplina : disciplinas) {
                    comboBoxDisciplina.addItem(disciplina);
                }
            }
        });

        // Ação do botão para registrar frequência
        botaoRegistrarFrequencia.addActionListener(e -> {
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem();
            Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplina.getSelectedItem();
            String presenca = (String) comboBoxPresenca.getSelectedItem();

            if (alunoSelecionado != null && disciplinaSelecionada != null && presenca != null) {
                boolean estaPresente = presenca.equals("Presente");
                Date data = new Date(); // Data atual
                Frequencia frequencia = new Frequencia(0, alunoSelecionado.getId(), disciplinaSelecionada.getId(), data,
                        estaPresente); // Usando boolean aqui
                frequenciaController.registrarFrequencia(frequencia);
                JOptionPane.showMessageDialog(null, "Frequência registrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione todas as opções.");
            }
        });

        return painel;
    }
}
