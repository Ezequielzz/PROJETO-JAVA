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

// Classe responsável pela interface gráfica para registro de frequência
public class FrequenciaPanel {
    // Controladores necessários para gerenciar a frequência, alunos, disciplinas e turmas
    private TurmaController turmaController;
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;
    private FrequenciaController frequenciaController;

    // Construtor da classe FrequenciaPanel que recebe os controladores como parâmetros
    public FrequenciaPanel(TurmaController turmaController, AlunoController alunoController,
            DisciplinaController disciplinaController, FrequenciaController frequenciaController) {
        this.turmaController = turmaController; // Inicializa o controlador de turmas
        this.alunoController = alunoController; // Inicializa o controlador de alunos
        this.disciplinaController = disciplinaController; // Inicializa o controlador de disciplinas
        this.frequenciaController = frequenciaController; // Inicializa o controlador de frequência
    }

    // Método que cria e retorna um painel para registrar a frequência
    public JPanel criarPainelRegistrarFrequencia() {
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

        // Label e JComboBox para selecionar presença
        JLabel labelPresenca = new JLabel("Presença:"); // Rótulo para presença
        JComboBox<String> comboBoxPresenca = new JComboBox<>(new String[] { "Presente", "Ausente" }); // ComboBox para selecionar presença
        painel.add(labelPresenca); // Adiciona o rótulo ao painel
        painel.add(comboBoxPresenca); // Adiciona a ComboBox ao painel

        // Botão para registrar a presença
        JButton botaoRegistrarFrequencia = new JButton("Registrar Frequência"); // Botão para registrar frequência
        painel.add(botaoRegistrarFrequencia); // Adiciona o botão ao painel

        // Preenchendo JComboBox de turmas com as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas(); // Obtém a lista de turmas do controlador
        for (Turma turma : turmas) { // Itera sobre as turmas
            comboBoxTurma.addItem(turma); // Adiciona cada turma ao ComboBox
        }

        // Listener para atualizar alunos e disciplinas ao selecionar uma turma
        comboBoxTurma.addActionListener(e -> { // Adiciona um ouvinte de ação ao ComboBox de turmas
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem(); // Obtém a turma selecionada
            if (turmaSelecionada != null) { // Verifica se uma turma foi selecionada
                // Atualizar lista de alunos
                comboBoxAluno.removeAllItems(); // Limpa os itens do ComboBox de alunos
                List<Aluno> alunos = alunoController.listarAlunosPorTurma(turmaSelecionada.getTurmaId()); // Obtém os alunos da turma selecionada
                for (Aluno aluno : alunos) { // Itera sobre os alunos
                    comboBoxAluno.addItem(aluno); // Adiciona cada aluno ao ComboBox
                }

                // Atualizar lista de disciplinas
                comboBoxDisciplina.removeAllItems(); // Limpa os itens do ComboBox de disciplinas
                List<Disciplina> disciplinas = disciplinaController
                        .buscarDisciplinasPorTurma(turmaSelecionada.getTurmaId()); // Obtém as disciplinas da turma selecionada
                for (Disciplina disciplina : disciplinas) { // Itera sobre as disciplinas
                    comboBoxDisciplina.addItem(disciplina); // Adiciona cada disciplina ao ComboBox
                }
            }
        });

        // Ação do botão para registrar frequência
        botaoRegistrarFrequencia.addActionListener(e -> { // Adiciona um ouvinte de ação ao botão
            Aluno alunoSelecionado = (Aluno) comboBoxAluno.getSelectedItem(); // Obtém o aluno selecionado
            Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplina.getSelectedItem(); // Obtém a disciplina selecionada
            String presenca = (String) comboBoxPresenca.getSelectedItem(); // Obtém a presença selecionada

            // Verifica se todos os campos estão preenchidos
            if (alunoSelecionado != null && disciplinaSelecionada != null && presenca != null) {
                boolean estaPresente = presenca.equals("Presente"); // Converte a seleção de presença para booleano
                Date data = new Date(); // Cria um objeto Date com a data atual
                // Cria um objeto Frequencia com os dados coletados
                Frequencia frequencia = new Frequencia(0, alunoSelecionado.getId(), disciplinaSelecionada.getId(), data,
                        estaPresente); // Cria um novo objeto de frequência
                frequenciaController.registrarFrequencia(frequencia); // Chama o controlador para registrar a frequência
                JOptionPane.showMessageDialog(null, "Frequência registrada com sucesso!"); // Mensagem de sucesso
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione todas as opções."); // Mensagem de erro se algum campo estiver vazio
            }
        });

        return painel; // Retorna o painel criado
    }
}
