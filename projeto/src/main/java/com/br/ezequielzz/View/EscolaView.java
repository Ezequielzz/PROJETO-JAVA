package com.br.ezequielzz.View;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.NotaController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
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

        // Painel para disciplinas
        JPanel disciplinaPanel = criarDisciplinaPanel();
        tabbedPane.addTab("Disciplinas", disciplinaPanel);

        // Painel para notas
        JPanel notaPanel = criarNotaPanel();
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
        String[] colunas = {"ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone", "Turma", " Situação Matrícula"};

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

                    // Obter o ID da turma selecionada no JComboBox
                    String turmaSelecionada = (String) turmaComboBox.getSelectedItem();
                    if (turmaSelecionada != null) {
                        int turmaId = Integer.parseInt(turmaSelecionada.split("-")[0].trim()); // Extrai o ID
                        // Inicializar o objeto aluno antes de usá-lo
                        aluno = new Aluno(); // Certifique-se de inicializar o objeto aluno corretamente
                        aluno.setTurmaId(turmaId); // Certifique-se de ter um método setTurmaId em Aluno
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma turma válida.");
                        return;
                    }

                    // Convertendo a data de nascimento
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = formatter.parse(dataNascimentoField.getText().trim());

                    // Criar objeto Aluno com todos os parâmetros necessários
                    aluno = new Aluno(0, nome, cpf, dataNascimento, endereco, telefone, senha, turmaId, statusMatricula);

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
                Disciplina disciplina = new Disciplina(0, nomeDisciplina, 0);  // Simplificado
                disciplinaController.criarDisciplina(disciplina);
                JOptionPane.showMessageDialog(null, "Disciplina criada com sucesso!");
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(new JLabel());  // Espaçamento
        panel.add(criarDisciplinaButton);

        return panel;
    }

    // Método para criar painel de Nota
    private JPanel criarNotaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel alunoIdLabel = new JLabel("ID do Aluno:");
        JTextField alunoIdField = new JTextField();
        JLabel disciplinaIdLabel = new JLabel("ID da Disciplina:");
        JTextField disciplinaIdField = new JTextField();
        JLabel valorNotaLabel = new JLabel("Nota:");
        JTextField valorNotaField = new JTextField();
        JButton registrarNotaButton = new JButton("Registrar Nota");

        registrarNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int alunoId = Integer.parseInt(alunoIdField.getText());
                int disciplinaId = Integer.parseInt(disciplinaIdField.getText());
                float valorNota = Float.parseFloat(valorNotaField.getText());

                notaController.registrarNota(alunoId, disciplinaId, valorNota, new java.util.Date());
                JOptionPane.showMessageDialog(null, "Nota registrada com sucesso!");
            }
        });

        panel.add(alunoIdLabel);
        panel.add(alunoIdField);
        panel.add(disciplinaIdLabel);
        panel.add(disciplinaIdField);
        panel.add(valorNotaLabel);
        panel.add(valorNotaField);
        panel.add(new JLabel());  // Espaçamento
        panel.add(registrarNotaButton);

        return panel;
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
        panel.add(new JLabel());  // Espaçamento
        panel.add(criarTurmaButton);

        return panel;
    }
}
