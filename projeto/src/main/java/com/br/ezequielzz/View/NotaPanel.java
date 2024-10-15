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

// Classe responsável pela interface gráfica para atribuição de notas
public class NotaPanel {
    // Controladores necessários para gerenciar turmas, alunos, disciplinas e notas
    private TurmaController turmaController;
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;
    private NotaController notaController;

    // Construtor da classe NotaPanel que recebe os controladores como parâmetros
    public NotaPanel(TurmaController turmaController, AlunoController alunoController,
            DisciplinaController disciplinaController, NotaController notaController) {
        this.turmaController = turmaController; // Inicializa o controlador de turmas
        this.alunoController = alunoController; // Inicializa o controlador de alunos
        this.disciplinaController = disciplinaController; // Inicializa o controlador de disciplinas
        this.notaController = notaController; // Inicializa o controlador de notas
    }

    // Método que cria e retorna um painel para atribuir notas
    public JPanel criarPainelAtribuirNota() {
        // Criação do painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Define o layout do painel como vertical

        // Label e JComboBox para selecionar Turma
        JLabel labelTurma = new JLabel("Selecione a Turma:"); // Rótulo para seleção de turma
        JComboBox<Turma> comboBoxTurma = new JComboBox<>(); // ComboBox para listar as turmas
        painel.add(labelTurma); // Adiciona o rótulo ao painel
        painel.add(comboBoxTurma); // Adiciona a ComboBox ao painel

        // Label e JComboBox para selecionar Aluno
        JLabel labelAluno = new JLabel("Selecione o Aluno:"); // Rótulo para seleção de aluno
        JComboBox<Aluno> comboBoxAluno = new JComboBox<>(); // ComboBox para listar os alunos
        painel.add(labelAluno); // Adiciona o rótulo ao painel
        painel.add(comboBoxAluno); // Adiciona a ComboBox ao painel

        // Label para exibir Disciplina
        JLabel labelDisciplina = new JLabel("Disciplina:"); // Rótulo para disciplina
        JComboBox<Disciplina> comboBoxDisciplina = new JComboBox<>(); // ComboBox para listar as disciplinas
        painel.add(labelDisciplina); // Adiciona o rótulo ao painel
        painel.add(comboBoxDisciplina); // Adiciona a ComboBox ao painel

        // Label e campo de input para a Nota
        JLabel labelNota = new JLabel("Informe a Nota:"); // Rótulo para entrada da nota
        JTextField campoNota = new JTextField(5); // Campo de texto para entrada da nota
        painel.add(labelNota); // Adiciona o rótulo ao painel
        painel.add(campoNota); // Adiciona o campo de texto ao painel

        // Botão para lançar a nota
        JButton botaoLancarNota = new JButton("Lançar Nota"); // Botão para lançar a nota
        painel.add(botaoLancarNota); // Adiciona o botão ao painel

        // Preenchendo o JComboBox de turmas com as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas(); // Obtém a lista de turmas do controlador
        for (Turma turma : turmas) { // Itera sobre as turmas
            comboBoxTurma.addItem(turma); // Adiciona cada turma ao ComboBox
        }

        // Listener para atualizar alunos e disciplina quando uma turma for selecionada
        comboBoxTurma.addActionListener(e -> { // Adiciona um ouvinte de ação ao ComboBox de turmas
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem(); // Obtém a turma selecionada
            if (turmaSelecionada != null) { // Verifica se uma turma foi selecionada
                // Atualiza a lista de alunos da turma
                comboBoxAluno.removeAllItems(); // Limpa os itens do ComboBox de alunos
                List<Aluno> alunos = alunoController.listarAlunosPorTurma(turmaSelecionada.getTurmaId()); // Obtém os alunos da turma selecionada
                for (Aluno aluno : alunos) { // Itera sobre os alunos
                    comboBoxAluno.addItem(aluno); // Adiciona cada aluno ao ComboBox
                }
                // Atualiza a lista de disciplinas da turma
                comboBoxDisciplina.removeAllItems(); // Limpa os itens do ComboBox de disciplinas
                List<Disciplina> disciplinas = disciplinaController
                        .buscarDisciplinasPorTurma(turmaSelecionada.getTurmaId()); // Obtém as disciplinas da turma selecionada
                for (Disciplina disciplina : disciplinas) { // Itera sobre as disciplinas
                    comboBoxDisciplina.addItem(disciplina); // Adiciona cada disciplina ao ComboBox
                }
            }
        });

        // Ação do botão de lançar nota
        botaoLancarNota.addActionListener(e -> { // Adiciona um ouvinte de ação ao botão
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem(); // Obtém o aluno selecionado
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem(); // Obtém a turma selecionada
            Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplina.getSelectedItem(); // Obtém a disciplina selecionada

            // Verifica se todos os campos estão preenchidos
            if (alunoSelecionado != null && turmaSelecionada != null && disciplinaSelecionada != null) {
                try {
                    float nota = Float.parseFloat(campoNota.getText()); // Tenta converter o texto da nota para float
                    // Verificar se a nota está dentro do intervalo correto
                    if (nota < 0 || nota > 10) { // Verifica se a nota está entre 0 e 10
                        JOptionPane.showMessageDialog(null, "A nota deve estar entre 0 e 10."); // Mensagem de erro
                        return; // Sai do método se a nota for inválida
                    }
                    // Registrar nota no banco de dados
                    notaController.registrarNota(alunoSelecionado.getId(), disciplinaSelecionada.getId(), nota,
                            new Date()); // Registra a nota com a data atual
                    JOptionPane.showMessageDialog(null, "Nota lançada com sucesso!"); // Mensagem de sucesso
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Informe uma nota válida."); // Mensagem de erro se a entrada não for válida
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione aluno, turma e verifique a disciplina."); // Mensagem de erro se algum campo estiver vazio
            }
        });

        return painel; // Retorna o painel criado
    }
}
