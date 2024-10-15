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

// Classe responsável pela interface gráfica para gerenciamento de disciplinas
public class DisciplinaPanel {
    // Controladores para gerenciar turmas, disciplinas e professores
    private TurmaController turmaController;
    private DisciplinaController disciplinaController;
    private ProfessorController professorController;

    // Construtor da classe DisciplinaPanel que recebe os controladores como parâmetros
    public DisciplinaPanel(TurmaController turmaController, DisciplinaController disciplinaController, ProfessorController professorController) {
        this.turmaController = turmaController; // Inicializa o controlador de turmas
        this.disciplinaController = disciplinaController; // Inicializa o controlador de disciplinas
        this.professorController = professorController; // Inicializa o controlador de professores
    }
    
    // Método que cria e retorna um painel para criar uma nova disciplina
    public JPanel criarPainelCriarDisciplina() {
        // Criação do painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Define o layout do painel como vertical

        // Label e campo de texto para nome da disciplina
        JLabel labelNome = new JLabel("Nome da Disciplina:"); // Cria um rótulo para o nome da disciplina
        JTextField campoNome = new JTextField(20); // Campo de texto para entrada do nome da disciplina
        painel.add(labelNome); // Adiciona o rótulo ao painel
        painel.add(campoNome); // Adiciona o campo de texto ao painel

        // Label e JComboBox para selecionar a turma
        JLabel labelTurma = new JLabel("Selecione a Turma:"); // Rótulo para seleção de turma
        JComboBox<Turma> comboBoxTurma = new JComboBox<>(); // ComboBox para listar as turmas
        painel.add(labelTurma); // Adiciona o rótulo ao painel
        painel.add(comboBoxTurma); // Adiciona a ComboBox ao painel

        // Label e JComboBox para selecionar o professor
        JLabel labelProfessor = new JLabel("Selecione o Professor:"); // Rótulo para seleção de professor
        JComboBox<Professor> comboBoxProfessor = new JComboBox<>(); // ComboBox para listar os professores
        painel.add(labelProfessor); // Adiciona o rótulo ao painel
        painel.add(comboBoxProfessor); // Adiciona a ComboBox ao painel

        // Botão para criar disciplina
        JButton botaoCriarDisciplina = new JButton("Criar Disciplina"); // Botão para criar uma nova disciplina
        painel.add(botaoCriarDisciplina); // Adiciona o botão ao painel

        // Preenchendo JComboBox com as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas(); // Obtém a lista de turmas do controlador
        for (Turma turma : turmas) { // Itera sobre as turmas
            comboBoxTurma.addItem(turma); // Adiciona cada turma ao ComboBox
        }

        // Preenchendo JComboBox com os professores do banco de dados
        List<Professor> professores = professorController.listarTodosProfessores(); // Obtém a lista de professores do controlador
        for (Professor professor : professores) { // Itera sobre os professores
            comboBoxProfessor.addItem(professor); // Adiciona cada professor ao ComboBox
        }

        // Ação do botão para criar a disciplina
        botaoCriarDisciplina.addActionListener(e -> { // Adiciona um ouvinte de ação ao botão
            String nomeDisciplina = campoNome.getText(); // Obtém o nome da disciplina do campo de texto
            Turma turmaSelecionada = (Turma) comboBoxTurma.getSelectedItem(); // Obtém a turma selecionada no ComboBox
            // Verifica se os campos estão preenchidos
            if (nomeDisciplina.isEmpty() || turmaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos."); // Mensagem de erro se campos vazios
            } else {
                // Cria um objeto Disciplina e chama o método do controlador para criar a disciplina
                Disciplina disciplina = new Disciplina(0, nomeDisciplina, turmaSelecionada.getTurmaId()); // Cria uma nova disciplina
                disciplinaController.criarDisciplina(disciplina); // Chama o controlador para criar a disciplina
                JOptionPane.showMessageDialog(null, "Disciplina criada com sucesso!"); // Mensagem de sucesso
            }
            
        });

        return painel; // Retorna o painel criado
    }
}
