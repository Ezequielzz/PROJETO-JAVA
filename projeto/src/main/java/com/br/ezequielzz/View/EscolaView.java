package com.br.ezequielzz.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
import com.br.ezequielzz.Model.Matricula;
import com.br.ezequielzz.Model.Professor;
import com.br.ezequielzz.Model.Turma;

public class EscolaView extends JFrame {
    private JTable tabelaAlunos;
    private JTable tabelaProfessores;
    private AlunoController alunoController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;
    private NotaController notaController;
    private TurmaController turmaController;
    private FrequenciaController frequenciaController;
    private RelatorioController relatorioController;
    private Aluno aluno;
    private Professor professor;

    public EscolaView() {
        alunoController = new AlunoController();
        disciplinaController = new DisciplinaController();
        notaController = new NotaController();
        turmaController = new TurmaController();
        frequenciaController = new FrequenciaController();
        relatorioController = new RelatorioController();
        professorController = new ProfessorController();

        // Configurações básicas do JFrame
        setTitle("Sistema de Gestão Escolar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel para listar professores
        JPanel listarProfessoresPanel = criarListarProfessoresPanel();
        tabbedPane.addTab("Listar Professores", listarProfessoresPanel);

        // Painel para alunos
        JPanel professorPanel = criarProfessorPanel();
        tabbedPane.addTab("Professor", professorPanel);

        // Painel para listar alunos
        JPanel listarAlunosPanel = criarListarAlunosPanel();
        tabbedPane.addTab("Listar Alunos", listarAlunosPanel);

        // Painel para alunos
        JPanel alunoPanel = criarAlunoPanel();
        tabbedPane.addTab("Alunos", alunoPanel);

        JPanel matriculaPanel = criarMatriculaPanel();
        tabbedPane.addTab("Matrículas", matriculaPanel);

        // Painel para notas
        JPanel notaPanel = criarPainelAtribuirNota();
        tabbedPane.addTab("Notas", notaPanel);

        // Painel para frequencia
        JPanel frequenciaPanel = criarPainelRegistrarFrequencia();
        tabbedPane.addTab("Frequência", frequenciaPanel);

        JPanel disciplinaPanel = criarPainelCriarDisciplina();
        tabbedPane.addTab("Disciplinas", disciplinaPanel);

        // Painel para turmas
        JPanel turmaPanel = criarPainelCriarTurma();
        tabbedPane.addTab("Turmas", turmaPanel);

        // Painel para relatorios
        JPanel relatorioPanel = criarPainelGerarRelatorio();
        tabbedPane.addTab("Relatorio", relatorioPanel);

        // Adicionar o tabbedPane ao JFrame
        add(tabbedPane, BorderLayout.CENTER);

        // Exibir a interface
        setVisible(true);
    }

    private JPanel criarProfessorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2)); // Ajuste para incluir todos os campos necessários

        // Campos necessários para criar um professor
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

        JButton criarProfessorButton = new JButton("Criar Professor");

        criarProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Capturar os valores dos campos
                    String nome = nomeField.getText().trim();
                    String cpf = cpfField.getText().trim();
                    String endereco = enderecoField.getText().trim();
                    String telefone = telefoneField.getText().trim();
                    String senha = senhaField.getText().trim();

                    // Convertendo a data de nascimento
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = formatter.parse(dataNascimentoField.getText().trim());

                    // Criar objeto Aluno com todos os parâmetros necessários
                    professor = new Professor(0, nome, cpf, dataNascimento, endereco, telefone, senha);

                    // Chamar o controlador para criar o aluno
                    boolean sucesso = professorController.criarProfessor(professor);

                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Professor criado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao criar professor.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar professor: " + ex.getMessage());
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

        panel.add(new JLabel()); // Espaçamento
        panel.add(criarProfessorButton);

        return panel;
    }

    private JPanel criarListarProfessoresPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Definir os nomes das colunas
        String[] colunas = { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone" };

        // Criar um DefaultTableModel inicialmente com dados vazios
        DefaultTableModel modeloTabela = new DefaultTableModel(new Object[0][6], colunas);
        tabelaProfessores = new JTable(modeloTabela); // Agora usando DefaultTableModel
        JScrollPane scrollPane = new JScrollPane(tabelaProfessores);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Inicializar a tabela com os dados
        atualizarTabelaProfessores();

        // Criar painel para os botões
        JPanel botaoPanel = new JPanel();

        // Botão de Excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaProfessores.getSelectedRow();
            if (linhaSelecionada != -1) {
                int professorId = (int) tabelaProfessores.getValueAt(linhaSelecionada, 0); // ID do professor
                try {
                    professorController.excluirProfessor(professorId);
                    JOptionPane.showMessageDialog(panel, "Professor excluído com sucesso!");
                    atualizarTabelaProfessores(); // Atualizar a tabela após exclusão
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Erro ao excluir professor: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecione um professor para excluir.");
            }
        });

        // Botão de Atualizar
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            int linhaSelecionada = tabelaProfessores.getSelectedRow();
            if (linhaSelecionada != -1) {
                int professorId = (int) tabelaProfessores.getValueAt(linhaSelecionada, 0); // ID do professor
                try {
                    Professor professor = professorController.buscarProfessorPorId(professorId);
                    if (professor != null) {
                        editarProfessorDialog(professor);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Professor não encontrado.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Erro ao buscar professor: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecione um professor para atualizar.");
            }
        });

        botaoPanel.add(btnAtualizar);
        botaoPanel.add(btnExcluir);
        panel.add(botaoPanel, BorderLayout.NORTH);

        return panel;
    }

    // Método para atualizar a tabela de professores
    private void atualizarTabelaProfessores() {
        List<Professor> professores = professorController.listarTodosProfessores();
        Object[][] dadosAtualizados = new Object[professores.size()][6]; // 6 colunas

        for (int i = 0; i < professores.size(); i++) {
            Professor professor = professores.get(i);
            dadosAtualizados[i][0] = professor.getId();
            dadosAtualizados[i][1] = professor.getNome();
            dadosAtualizados[i][2] = professor.getCpf();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dadosAtualizados[i][3] = sdf.format(professor.getDataNascimento());
            dadosAtualizados[i][4] = professor.getEndereco();
            dadosAtualizados[i][5] = professor.getTelefone();
        }

        // Atualizar os dados na tabela
        DefaultTableModel model = (DefaultTableModel) tabelaProfessores.getModel();
        model.setDataVector(dadosAtualizados,
                new String[] { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone" });
    }

    // Diálogo de edição de professor
    private void editarProfessorDialog(Professor professor) {
        // Criar um diálogo para editar os dados
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Professor");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));

        // Campos de texto para editar os dados do professor
        JTextField nomeField = new JTextField(professor.getNome());
        JTextField cpfField = new JTextField(professor.getCpf());
        JTextField enderecoField = new JTextField(professor.getEndereco());
        JTextField telefoneField = new JTextField(professor.getTelefone());

        // Adicionar os campos ao diálogo
        dialog.add(new JLabel("Nome:"));
        dialog.add(nomeField);
        dialog.add(new JLabel("CPF:"));
        dialog.add(cpfField);
        dialog.add(new JLabel("Endereço:"));
        dialog.add(enderecoField);
        dialog.add(new JLabel("Telefone:"));
        dialog.add(telefoneField);

        // Botão de salvar as alterações
        JButton salvarBtn = new JButton("Salvar");
        salvarBtn.addActionListener(e -> {
            // Atualizar os dados do professor com os valores do formulário
            professor.setNome(nomeField.getText());
            professor.setCpf(cpfField.getText());
            professor.setEndereco(enderecoField.getText());
            professor.setTelefone(telefoneField.getText());

            // Chamar o método de atualização no controller
            try {
                professorController.atualizarProfessor(professor);
                JOptionPane.showMessageDialog(dialog, "Professor atualizado com sucesso!");
                dialog.dispose();
                atualizarTabelaProfessores(); // Atualizar a tabela após a edição
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao atualizar professor: " + ex.getMessage());
            }
        });

        dialog.add(new JLabel()); // Espaçamento
        dialog.add(salvarBtn);

        dialog.setVisible(true);
    }

    private JPanel criarListarAlunosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Definir os nomes das colunas
        String[] colunas = { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone", "Turma",
                "Situação Matrícula" };

        // Criar um DefaultTableModel inicialmente com dados vazios
        DefaultTableModel modeloTabela = new DefaultTableModel(new Object[0][8], colunas);
        tabelaAlunos = new JTable(modeloTabela); // Agora usando DefaultTableModel
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Inicializar a tabela com os dados
        atualizarTabelaAlunos();

        // Criar painel para os botões
        JPanel botaoPanel = new JPanel();

        // Botão de Excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaAlunos.getSelectedRow();
            if (linhaSelecionada != -1) {
                int alunoId = (int) tabelaAlunos.getValueAt(linhaSelecionada, 0); // ID do aluno
                try {
                    alunoController.excluirAluno(alunoId);
                    JOptionPane.showMessageDialog(panel, "Aluno excluído com sucesso!");
                    atualizarTabelaAlunos(); // Atualizar a tabela após exclusão
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Erro ao excluir aluno: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecione um aluno para excluir.");
            }
        });

        // Botão de Atualizar
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            int linhaSelecionada = tabelaAlunos.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Obter o ID do aluno selecionado
                int alunoId = (int) tabelaAlunos.getValueAt(linhaSelecionada, 0);
                try {
                    // Buscar os dados completos do aluno
                    Aluno aluno = alunoController.buscarAlunoPorId(alunoId);
                    // Abrir um diálogo para edição
                    if (aluno != null) {
                        editarAlunoDialog(aluno);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Aluno não encontrado.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Erro ao buscar aluno: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecione um aluno para atualizar.");
            }
        });

        botaoPanel.add(btnAtualizar);
        botaoPanel.add(btnExcluir);
        panel.add(botaoPanel, BorderLayout.NORTH);

        return panel;
    }

    // Método para atualizar a tabela de alunos
    private void atualizarTabelaAlunos() {
        List<Aluno> alunos = alunoController.listarTodosAlunos();
        Object[][] dadosAtualizados = new Object[alunos.size()][8]; // 8 colunas

        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            dadosAtualizados[i][0] = aluno.getId();
            dadosAtualizados[i][1] = aluno.getNome();
            dadosAtualizados[i][2] = aluno.getCpf();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dadosAtualizados[i][3] = sdf.format(aluno.getDataNascimento());
            dadosAtualizados[i][4] = aluno.getEndereco();
            dadosAtualizados[i][5] = aluno.getTelefone();
            dadosAtualizados[i][6] = aluno.getTurmaId();
            dadosAtualizados[i][7] = aluno.getStatusMatricula();
        }

        // Atualizar os dados na tabela
        DefaultTableModel model = (DefaultTableModel) tabelaAlunos.getModel();
        model.setDataVector(dadosAtualizados, new String[] { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço",
                "Telefone", "Turma", "Situação Matrícula" });
    }

    // Diálogo de edição de aluno
    private void editarAlunoDialog(Aluno aluno) {
        // Criar um diálogo para editar os dados
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Aluno");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));

        // Campos de texto para editar os dados do aluno
        JTextField nomeField = new JTextField(aluno.getNome());
        JTextField cpfField = new JTextField(aluno.getCpf());
        JTextField enderecoField = new JTextField(aluno.getEndereco());
        JTextField telefoneField = new JTextField(aluno.getTelefone());
        JTextField senhaField = new JTextField(aluno.getSenha());

        // Adicionar os campos ao diálogo
        dialog.add(new JLabel("Nome:"));
        dialog.add(nomeField);
        dialog.add(new JLabel("CPF:"));
        dialog.add(cpfField);
        dialog.add(new JLabel("Endereço:"));
        dialog.add(enderecoField);
        dialog.add(new JLabel("Telefone:"));
        dialog.add(telefoneField);
        dialog.add(new JLabel("Senha:"));
        dialog.add(senhaField);

        // Botão de salvar as alterações
        JButton salvarBtn = new JButton("Salvar");
        salvarBtn.addActionListener(e -> {
            // Atualizar os dados do aluno com os valores do formulário
            aluno.setNome(nomeField.getText());
            aluno.setCpf(cpfField.getText());
            aluno.setEndereco(enderecoField.getText());
            aluno.setTelefone(telefoneField.getText());
            aluno.setSenha(senhaField.getText());

            // Chamar o método de atualização no controller
            try {
                alunoController.atualizarAluno(aluno);
                JOptionPane.showMessageDialog(dialog, "Aluno atualizado com sucesso!");
                dialog.dispose();
                atualizarTabelaAlunos(); // Atualizar a tabela após a edição
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao atualizar aluno: " + ex.getMessage());
            }
        });

        dialog.add(new JLabel()); // Espaçamento
        dialog.add(salvarBtn);

        dialog.setVisible(true);
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
