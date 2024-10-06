package com.br.ezequielzz.View;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.MatriculaController;
import com.br.ezequielzz.Controller.NotaController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.Matricula;
import com.br.ezequielzz.Model.Turma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EscolaView extends JFrame {
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;
    private NotaController notaController;
    private TurmaController turmaController;
    private Aluno aluno;

    public EscolaView() {
        alunoController = new AlunoController();
        disciplinaController = new DisciplinaController();
        notaController = new NotaController();
        turmaController = new TurmaController();

        // Configurações básicas do JFrame
        setTitle("Sistema de Gestão Escolar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel para listar alunos
        JPanel listarAlunosPanel = criarListarAlunosPanel();
        tabbedPane.addTab("Listar Alunos", listarAlunosPanel);

        // Painel para alunos
        JPanel alunoPanel = criarAlunoPanel();
        tabbedPane.addTab("Alunos", alunoPanel);

        JPanel matriculaPanel = criarMatriculaPanel();
        tabbedPane.addTab("Matrículas", matriculaPanel);

        // Painel para disciplinas
        JPanel disciplinaPanel = criarDisciplinaPanel();
        tabbedPane.addTab("Disciplinas", disciplinaPanel);

        // Painel para notas
        JPanel notaPanel = criarPainelAtribuirNota();
        tabbedPane.addTab("Notas", notaPanel);

        // Painel para turmas
        JPanel turmaPanel = criarTurmaPanel();
        tabbedPane.addTab("Turmas", turmaPanel);

        // Adicionar o tabbedPane ao JFrame
        add(tabbedPane, BorderLayout.CENTER);

        // Exibir a interface
        setVisible(true);
    }

    private JPanel criarListarAlunosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Definir os nomes das colunas
        String[] colunas = { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone", "Turma",
                " Situação Matrícula" };

        // Obter os dados dos alunos
        java.util.List<Aluno> alunos = alunoController.listarTodosAlunos();
        Object[][] dados = new Object[alunos.size()][8];

        // Preencher a tabela com os dados dos alunos
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            dados[i][0] = aluno.getId();
            dados[i][1] = aluno.getNome();
            dados[i][2] = aluno.getCpf();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dados[i][3] = sdf.format(aluno.getDataNascimento());
            dados[i][4] = aluno.getEndereco();
            dados[i][5] = aluno.getTelefone();
            dados[i][6] = aluno.getTurmaId();
            dados[i][7] = aluno.getStatusMatricula();
        }

        // Criar a tabela
        JTable tabelaAlunos = new JTable(dados, colunas);

        // Adicionar a tabela a um JScrollPane para permitir a rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel criarAlunoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2)); // Ajuste para incluir todos os campos necessários

        // Campos necessários para criar um aluno
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento (dd/MM/yyyy):");
        JTextField dataNascimentoField = new JTextField();

        JLabel enderecoLabel = new JLabel("Endereço:");
        JTextField enderecoField = new JTextField();

        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        JTextField senhaField = new JTextField();

        JLabel turmaLabel = new JLabel("Turma:");
        JComboBox<String> turmaComboBox = new JComboBox<>();

        // Método para carregar as turmas no JComboBox
        loadTurmas(turmaComboBox);

        JLabel statusMatriculaLabel = new JLabel("Status da Matrícula:");
        JTextField statusMatriculaField = new JTextField();

        JButton criarAlunoButton = new JButton("Criar Aluno");

        criarAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Capturar os valores dos campos
                    String nome = nomeField.getText().trim();
                    String cpf = cpfField.getText().trim();
                    String endereco = enderecoField.getText().trim();
                    String telefone = telefoneField.getText().trim();
                    String senha = senhaField.getText().trim();
                    String statusMatricula = statusMatriculaField.getText().trim();

                    // Variável turmaId definida fora do bloco if
                    int turmaId = -1; // Valor inicial para verificação

                    // Obter o ID da turma selecionada no JComboBox
                    String turmaSelecionada = (String) turmaComboBox.getSelectedItem();
                    if (turmaSelecionada != null) {
                        turmaId = Integer.parseInt(turmaSelecionada.split("-")[0].trim()); // Extrai o ID
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma turma válida.");
                        return;
                    }

                    // Convertendo a data de nascimento
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = formatter.parse(dataNascimentoField.getText().trim());

                    // Criar objeto Aluno com todos os parâmetros necessários
                    aluno = new Aluno(0, nome, cpf, dataNascimento, endereco, telefone, senha, turmaId,
                            statusMatricula);

                    // Chamar o controlador para criar o aluno
                    boolean sucesso = alunoController.criarAluno(aluno);

                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Aluno criado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao criar aluno.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar aluno: " + ex.getMessage());
                }
            }
        });

        // Adicionar os campos ao painel
        panel.add(nomeLabel);
        panel.add(nomeField);

        panel.add(cpfLabel);
        panel.add(cpfField);

        panel.add(dataNascimentoLabel);
        panel.add(dataNascimentoField);

        panel.add(enderecoLabel);
        panel.add(enderecoField);

        panel.add(telefoneLabel);
        panel.add(telefoneField);

        panel.add(senhaLabel);
        panel.add(senhaField);

        panel.add(turmaLabel);
        panel.add(turmaComboBox);

        panel.add(statusMatriculaLabel);
        panel.add(statusMatriculaField);

        panel.add(new JLabel()); // Espaçamento
        panel.add(criarAlunoButton);

        return panel;
    }

    private void loadTurmas(JComboBox<String> turmaComboBox) {
        // Exemplo de como carregar as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas(); // Método que deve retornar a lista de turmas
        for (Turma turma : turmas) {
            turmaComboBox.addItem(turma.getTurmaId() + " - " + turma.getSerie()); // Exibe o ID e a série
        }
    }

    private JPanel criarMatriculaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Label e JComboBox para selecionar o aluno
        JLabel alunoLabel = new JLabel("Aluno:");
        JComboBox<String> alunoComboBox = new JComboBox<>();
        loadAlunos(alunoComboBox); // Carregar os alunos no ComboBox

        // Label e JComboBox para selecionar a turma
        JLabel turmaLabel = new JLabel("Turma:");
        JComboBox<String> turmaComboBox = new JComboBox<>();
        loadTurmas(turmaComboBox); // Carregar as turmas no ComboBox

        // Label e campo para data de matrícula
        JLabel dataMatriculaLabel = new JLabel("Data de Matrícula:");
        JTextField dataMatriculaField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));

        // Label e JComboBox para status da matrícula
        JLabel statusLabel = new JLabel("Status:");
        JComboBox<String> statusComboBox = new JComboBox<>(new String[] { "matriculado", "cancelado", "pendente" });

        // Botão para efetuar a matrícula
        JButton matricularButton = new JButton("Realizar Matrícula");
        matricularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obter o aluno e turma selecionados
                    String alunoSelecionado = (String) alunoComboBox.getSelectedItem();
                    String turmaSelecionada = (String) turmaComboBox.getSelectedItem();
                    if (alunoSelecionado == null || turmaSelecionada == null) {
                        JOptionPane.showMessageDialog(null, "Selecione um aluno e uma turma.");
                        return;
                    }

                    int alunoId = Integer.parseInt(alunoSelecionado.split("-")[0].trim());
                    int turmaId = Integer.parseInt(turmaSelecionada.split("-")[0].trim());

                    // Obter a data de matrícula e o status
                    String dataMatriculaStr = dataMatriculaField.getText().trim();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date dataMatricula = formatter.parse(dataMatriculaStr);
                    String status = (String) statusComboBox.getSelectedItem();

                    // Criar o objeto Matricula
                    Matricula matricula = new Matricula(0, alunoId, turmaId, dataMatricula, status);

                    // Chamar o controller para realizar a matrícula
                    MatriculaController matriculaController = new MatriculaController();
                    matriculaController.realizarMatricula(matricula);

                    JOptionPane.showMessageDialog(null, "Matrícula realizada com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao realizar matrícula: " + ex.getMessage());
                }
            }
        });

        // Adicionar os componentes ao painel
        panel.add(alunoLabel);
        panel.add(alunoComboBox);

        panel.add(turmaLabel);
        panel.add(turmaComboBox);

        panel.add(dataMatriculaLabel);
        panel.add(dataMatriculaField);

        panel.add(statusLabel);
        panel.add(statusComboBox);

        panel.add(new JLabel()); // Espaçamento
        panel.add(matricularButton);

        return panel;
    }

    private void loadAlunos(JComboBox<String> alunoComboBox) {
        List<Aluno> alunos = alunoController.listarTodosAlunos();
        for (Aluno aluno : alunos) {
            alunoComboBox.addItem(aluno.getId() + " - " + aluno.getNome());
        }
    }

    // Método para criar painel de Disciplina
    private JPanel criarDisciplinaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nomeLabel = new JLabel("Nome da Disciplina:");
        JTextField nomeField = new JTextField();
        JButton criarDisciplinaButton = new JButton("Criar Disciplina");

        criarDisciplinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDisciplina = nomeField.getText();
                Disciplina disciplina = new Disciplina(0, nomeDisciplina, 0); // Simplificado
                disciplinaController.criarDisciplina(disciplina);
                JOptionPane.showMessageDialog(null, "Disciplina criada com sucesso!");
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(new JLabel()); // Espaçamento
        panel.add(criarDisciplinaButton);

        return panel;
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
                List<Disciplina> disciplinas = disciplinaController.buscarDisciplinasPorTurma(turmaSelecionada.getTurmaId());
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
                    notaController.registrarNota(alunoSelecionado.getId(), disciplinaSelecionada.getId(), nota, new Date());
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

    // Método para criar painel de Turma
    private JPanel criarTurmaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel serieLabel = new JLabel("Série:");
        JTextField serieField = new JTextField();
        JLabel anoLetivoLabel = new JLabel("Ano Letivo:");
        JTextField anoLetivoField = new JTextField();
        JButton criarTurmaButton = new JButton("Criar Turma");

        criarTurmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serie = serieField.getText();
                String anoLetivo = anoLetivoField.getText();

                Turma turma = new Turma(0, serie, anoLetivo, "", "");
                turmaController.criarTurma(turma);
                JOptionPane.showMessageDialog(null, "Turma criada com sucesso!");
            }
        });

        panel.add(serieLabel);
        panel.add(serieField);
        panel.add(anoLetivoLabel);
        panel.add(anoLetivoField);
        panel.add(new JLabel()); // Espaçamento
        panel.add(criarTurmaButton);

        return panel;
    }
}
