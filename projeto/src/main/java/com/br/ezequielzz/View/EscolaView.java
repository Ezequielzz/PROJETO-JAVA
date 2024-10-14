package com.br.ezequielzz.View;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.FrequenciaController;
import com.br.ezequielzz.Controller.MatriculaController;
import com.br.ezequielzz.Controller.NotaController;
import com.br.ezequielzz.Controller.ProfessorController;
import com.br.ezequielzz.Controller.RelatorioController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Frequencia;
import com.br.ezequielzz.Model.Professor;
import com.br.ezequielzz.Model.Turma;

public class EscolaView extends JFrame {
    private AlunoController alunoController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;
    private NotaController notaController;
    private TurmaController turmaController;
    private FrequenciaController frequenciaController;
    private RelatorioController relatorioController;
    private MatriculaController matriculaController;

    public EscolaView() {
        // Inicialização dos controladores
        alunoController = new AlunoController();
        disciplinaController = new DisciplinaController();
        notaController = new NotaController();
        turmaController = new TurmaController();
        frequenciaController = new FrequenciaController();
        relatorioController = new RelatorioController();
        professorController = new ProfessorController();
        matriculaController = new MatriculaController();

        // Criando as instâncias dos Panels
        ProfessorPanel professorPanel = new ProfessorPanel(professorController);
        AlunoPanel alunoPanel = new AlunoPanel(alunoController, turmaController);
        MatriculaPanel matriculaPanel = new MatriculaPanel(matriculaController, alunoController, turmaController);

        // Configurações básicas do JFrame
        setTitle("Sistema de Gestão Escolar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel para listar professores (usando ProfessorPanel)
        JPanel listarProfessoresPanel = professorPanel.criarListarProfessoresPanel();
        tabbedPane.addTab("Listar Professores", listarProfessoresPanel);

        // Painel para criar professor (usando ProfessorPanel)
        JPanel professorCriacaoPanel = professorPanel.criarProfessorPanel();
        tabbedPane.addTab("Professor", professorCriacaoPanel);

        // Painel para listar alunos
        JPanel listarAlunosPanel = alunoPanel.criarListarAlunosPanel(); // Mantenha como está
        tabbedPane.addTab("Listar Alunos", listarAlunosPanel);

        // Painel para alunos
        JPanel alunoCriacaoPanel = alunoPanel.criarAlunoPanel(); // Mantenha como está
        tabbedPane.addTab("Alunos", alunoCriacaoPanel);

        // Painel para matrículas
        JPanel matriculaCriacaoPanel = matriculaPanel.criarMatriculaPanel(); // Mantenha como está
        tabbedPane.addTab("Matrículas", matriculaCriacaoPanel);

        // Painel para notas
        JPanel notaPanel = criarPainelAtribuirNota(); // Mantenha como está
        tabbedPane.addTab("Notas", notaPanel);

        // Painel para frequencia
        JPanel frequenciaPanel = criarPainelRegistrarFrequencia(); // Mantenha como está
        tabbedPane.addTab("Frequência", frequenciaPanel);

        // Painel para disciplinas
        JPanel disciplinaPanel = criarPainelCriarDisciplina(); // Mantenha como está
        tabbedPane.addTab("Disciplinas", disciplinaPanel);

        // Painel para turmas
        JPanel turmaPanel = criarPainelCriarTurma(); // Mantenha como está
        tabbedPane.addTab("Turmas", turmaPanel);

        // Painel para relatórios
        JPanel relatorioPanel = criarPainelGerarRelatorio(); // Mantenha como está
        tabbedPane.addTab("Relatório", relatorioPanel);

        // Adicionar o tabbedPane ao JFrame
        add(tabbedPane, BorderLayout.CENTER);

        // Exibir a interface
        setVisible(true);
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

        // Label e campo de texto para turno
        JLabel labelTurno = new JLabel("Turno:");
        JTextField campoTurno = new JTextField(20);
        painel.add(labelTurno);
        painel.add(campoTurno);

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
            String turno = campoTurno.getText();
            String sala = campoSala.getText();

            if (serie.isEmpty() || anoLetivo.isEmpty() || turno.isEmpty() || sala.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            } else {
                Turma turma = new Turma(0, serie, anoLetivo, turno, sala);
                turmaController.criarTurma(turma);
                JOptionPane.showMessageDialog(null, "Turma criada com sucesso!");
            }
        });

        return painel;
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
