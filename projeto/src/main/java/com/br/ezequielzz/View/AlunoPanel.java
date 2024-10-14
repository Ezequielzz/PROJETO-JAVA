package com.br.ezequielzz.View;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Turma;

public class AlunoPanel {

    private AlunoController alunoController; // Controlador para gerenciar alunos
    private TurmaController turmaController; // Controlador para gerenciar turmas
    private JTable tabelaAlunos; // Tabela para exibir os alunos

    // Declarar os campos como atributos da classe por conta das Máscaras
    private JTextField nomeField; // Campo de texto para nome do aluno
    private JFormattedTextField cpfField; // Campo formatado para CPF
    private JFormattedTextField dataNascimentoField; // Campo formatado para data de nascimento
    private JTextField enderecoField; // Campo de texto para endereço do aluno
    private JFormattedTextField telefoneField; // Campo formatado para telefone
    private JTextField senhaField; // Campo de texto para senha do aluno
    private JComboBox<String> turmaComboBox; // ComboBox para seleção da turma

    // Construtor da classe AlunoPanel, inicializa os controladores
    public AlunoPanel(AlunoController alunoController, TurmaController turmaController) {
        this.alunoController = alunoController;
        this.turmaController = turmaController;
    }

    // Método para criar o painel que lista os alunos
    public JPanel criarListarAlunosPanel() {
        JPanel panel = new JPanel(); // Painel principal
        panel.setLayout(new BorderLayout()); // Define o layout do painel

        // Definir os nomes das colunas
        String[] colunas = { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone", "Turma",
                "Situação Matrícula" };

        // Criar um DefaultTableModel inicialmente com dados vazios
        DefaultTableModel modeloTabela = new DefaultTableModel(new Object[0][8], colunas); // Modelo da tabela
        tabelaAlunos = new JTable(modeloTabela); // Inicializa a tabela com o modelo
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos); // Cria uma barra de rolagem para a tabela
        panel.add(scrollPane, BorderLayout.CENTER); // Adiciona a barra de rolagem ao painel

        // Inicializar a tabela com os dados
        atualizarTabelaAlunos(); // Atualiza a tabela com os dados dos alunos

        // Criar painel para os botões
        JPanel botaoPanel = new JPanel(); // Painel para os botões

        // Adicionar o botão de "Criar Novo Aluno" no painel de listagem
        JButton btnCriarNovoAluno = new JButton("Criar Novo Aluno");
        btnCriarNovoAluno.addActionListener(e -> {
            JPanel criarAlunoPanel = criarAlunoPanel(); // Chama o método que retorna o formulário de criação
            JOptionPane.showMessageDialog(null, criarAlunoPanel, "Criar Novo Aluno", JOptionPane.PLAIN_MESSAGE);
        });

        // Adicionar o botão ao painel de botões na parte superior
        botaoPanel.add(btnCriarNovoAluno);

        // Botão de Excluir
        JButton btnExcluir = new JButton("Excluir"); // Botão para excluir um aluno
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaAlunos.getSelectedRow(); // Obtém a linha selecionada
            if (linhaSelecionada != -1) { // Verifica se uma linha está selecionada
                int alunoId = (int) tabelaAlunos.getValueAt(linhaSelecionada, 0); // ID do aluno
                try {
                    alunoController.excluirAluno(alunoId); // Chama o método para excluir o aluno
                    JOptionPane.showMessageDialog(panel, "Aluno excluído com sucesso!"); // Mensagem de sucesso
                    atualizarTabelaAlunos(); // Atualizar a tabela após exclusão
                } catch (SQLException ex) {
                    // Mensagem de erro se ocorrer um problema ao excluir
                    JOptionPane.showMessageDialog(panel, "Erro ao excluir aluno: " + ex.getMessage());
                }
            } else {
                // Mensagem para informar que deve-se selecionar um aluno
                JOptionPane.showMessageDialog(panel, "Selecione um aluno para excluir.");
            }
        });

        // Botão de Atualizar
        JButton btnAtualizar = new JButton("Atualizar"); // Botão para atualizar um aluno
        btnAtualizar.addActionListener(e -> {
            int linhaSelecionada = tabelaAlunos.getSelectedRow(); // Obtém a linha selecionada
            if (linhaSelecionada != -1) { // Verifica se uma linha está selecionada
                // Obter o ID do aluno selecionado
                int alunoId = (int) tabelaAlunos.getValueAt(linhaSelecionada, 0);
                try {
                    // Buscar os dados completos do aluno
                    Aluno aluno = alunoController.buscarAlunoPorId(alunoId);
                    // Abrir um diálogo para edição
                    if (aluno != null) { // Verifica se o aluno foi encontrado
                        editarAlunoDialog(aluno); // Chama o método para editar o aluno
                    } else {
                        // Mensagem caso o aluno não seja encontrado
                        JOptionPane.showMessageDialog(panel, "Aluno não encontrado.");
                    }
                } catch (SQLException ex) {
                    // Mensagem de erro se ocorrer um problema ao buscar o aluno
                    JOptionPane.showMessageDialog(panel, "Erro ao buscar aluno: " + ex.getMessage());
                }
            } else {
                // Mensagem para informar que deve-se selecionar um aluno
                JOptionPane.showMessageDialog(panel, "Selecione um aluno para atualizar.");
            }
        });

        botaoPanel.add(btnAtualizar); // Adiciona o botão de atualizar ao painel de botões
        botaoPanel.add(btnExcluir); // Adiciona o botão de excluir ao painel de botões
        panel.add(botaoPanel, BorderLayout.NORTH); // Adiciona o painel de botões ao painel principal

        return panel; // Retorna o painel criado
    }

    // Método para atualizar a tabela de alunos
    private void atualizarTabelaAlunos() {
        List<Aluno> alunos = alunoController.listarTodosAlunos(); // Lista todos os alunos
        Object[][] dadosAtualizados = new Object[alunos.size()][9]; // 9 colunas

        for (int i = 0; i < alunos.size(); i++) { // Percorre a lista de alunos
            Aluno aluno = alunos.get(i); // Obtém o aluno atual
            dadosAtualizados[i][0] = aluno.getId(); // ID do aluno
            dadosAtualizados[i][1] = aluno.getNome(); // Nome do aluno
            dadosAtualizados[i][2] = aluno.getCpf(); // CPF do aluno
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
            dadosAtualizados[i][3] = sdf.format(aluno.getDataNascimento()); // Data de nascimento formatada
            dadosAtualizados[i][4] = aluno.getEndereco(); // Endereço do aluno
            dadosAtualizados[i][5] = aluno.getTelefone(); // Telefone do aluno
            dadosAtualizados[i][6] = aluno.getTurmaId(); // ID da turma do aluno
            dadosAtualizados[i][7] = aluno.getStatusMatricula(); // Status da matrícula do aluno
        }

        // Atualizar os dados na tabela
        DefaultTableModel model = (DefaultTableModel) tabelaAlunos.getModel(); // Obtém o modelo da tabela
        model.setDataVector(dadosAtualizados, new String[] { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço",
                "Telefone", "Turma", "Situação Matrícula" }); // Atualiza os dados e colunas
    }

    // Diálogo de edição de aluno
    public void editarAlunoDialog(Aluno aluno) {
        // Criar um diálogo para editar os dados
        JDialog dialog = new JDialog(); // Cria um novo diálogo
        dialog.setTitle("Editar Aluno"); // Define o título do diálogo
        dialog.setSize(400, 300); // Define o tamanho do diálogo
        dialog.setLayout(new GridLayout(0, 2)); // Define o layout do diálogo como uma grade

        // Campos de texto para editar os dados do aluno
        JTextField nomeField = new JTextField(aluno.getNome()); // Campo para o nome
        JTextField cpfField = new JTextField(aluno.getCpf()); // Campo para o CPF
        JTextField enderecoField = new JTextField(aluno.getEndereco()); // Campo para o endereço
        JTextField telefoneField = new JTextField(aluno.getTelefone()); // Campo para o telefone
        JTextField senhaField = new JTextField(aluno.getSenha()); // Campo para a senha

        // Adicionar os campos ao diálogo
        dialog.add(new JLabel("Nome:")); // Rótulo para o nome
        dialog.add(nomeField); // Campo para o nome
        dialog.add(new JLabel("CPF:")); // Rótulo para o CPF
        dialog.add(cpfField); // Campo para o CPF
        dialog.add(new JLabel("Endereço:")); // Rótulo para o endereço
        dialog.add(enderecoField); // Campo para o endereço
        dialog.add(new JLabel("Telefone:")); // Rótulo para o telefone
        dialog.add(telefoneField); // Campo para o telefone
        dialog.add(new JLabel("Senha:")); // Rótulo para a senha
        dialog.add(senhaField); // Campo para a senha

        // Botão de salvar as alterações
        JButton salvarBtn = new JButton("Salvar"); // Botão para salvar as alterações
        salvarBtn.addActionListener(e -> {
            // Atualizar os dados do aluno com os valores do formulário
            aluno.setNome(nomeField.getText()); // Atualiza o nome
            aluno.setCpf(cpfField.getText()); // Atualiza o CPF
            aluno.setEndereco(enderecoField.getText()); // Atualiza o endereço
            aluno.setTelefone(telefoneField.getText()); // Atualiza o telefone
            aluno.setSenha(senhaField.getText()); // Atualiza a senha

            // Chamar o método de atualização no controller
            try {
                alunoController.atualizarAluno(aluno); // Atualiza o aluno no controlador
                JOptionPane.showMessageDialog(dialog, "Aluno atualizado com sucesso!"); // Mensagem de sucesso
                dialog.dispose(); // Fecha o diálogo
                atualizarTabelaAlunos(); // Atualiza a tabela após a edição
            } catch (SQLException ex) {
                // Mensagem de erro se ocorrer um problema ao atualizar o aluno
                JOptionPane.showMessageDialog(dialog, "Erro ao atualizar aluno: " + ex.getMessage());
            }
        });

        dialog.add(new JLabel()); // Espaçamento
        dialog.add(salvarBtn); // Adiciona o botão de salvar ao diálogo

        dialog.setVisible(true); // Exibe o diálogo
    }

    // Método para criar o painel de criação de aluno
    public JPanel criarAlunoPanel() {
        JPanel panel = new JPanel(); // Painel principal
        panel.setLayout(new GridLayout(10, 2)); // Define o layout do painel como uma grade

        // Inicializar os campos de texto
        JLabel nomeLabel = new JLabel("Nome:"); // Rótulo para o nome
        nomeField = new JTextField(); // Campo para o nome

        JLabel cpfLabel = new JLabel("CPF:"); // Rótulo para o CPF
        cpfField = null; // Inicializa o campo de CPF

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento (dd/MM/yyyy):"); // Rótulo para a data de nascimento
        dataNascimentoField = null; // Inicializa o campo de data de nascimento

        JLabel enderecoLabel = new JLabel("Endereço:"); // Rótulo para o endereço
        enderecoField = new JTextField(); // Campo para o endereço

        JLabel telefoneLabel = new JLabel("Telefone:"); // Rótulo para o telefone
        telefoneField = null; // Inicializa o campo de telefone

        // Inicialização dos campos formatados
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##"); // Máscara para o CPF
            cpfMask.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado
            cpfField = new JFormattedTextField(cpfMask); // Campo formatado para CPF

            MaskFormatter dataNascimentoMask = new MaskFormatter("##/##/####"); // Máscara para a data de nascimento
            dataNascimentoMask.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado
            dataNascimentoField = new JFormattedTextField(dataNascimentoMask); // Campo formatado para data de
                                                                               // nascimento

            MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####"); // Máscara para o telefone
            telefoneMask.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado
            telefoneField = new JFormattedTextField(telefoneMask); // Campo formatado para telefone

        } catch (ParseException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro ao aplicar a máscara
        }

        JLabel senhaLabel = new JLabel("Senha:"); // Rótulo para a senha
        senhaField = new JTextField(); // Campo para a senha

        JLabel turmaLabel = new JLabel("Turma:"); // Rótulo para a turma
        turmaComboBox = new JComboBox<>(); // ComboBox para seleção da turma

        // Método para carregar as turmas no JComboBox
        loadTurmas(turmaComboBox); // Carrega as turmas no ComboBox

        JLabel statusMatriculaLabel = new JLabel("Status da Matrícula:"); // Rótulo para o status da matrícula
        JComboBox<String> statusComboBox = new JComboBox<>(new String[] { "ativo" }); // ComboBox para status da
                                                                                      // matrícula

        JButton criarAlunoButton = new JButton("Criar Aluno"); // Botão para criar um novo aluno

        // Adicionando o ActionListener ao botão
        criarAlunoButton.addActionListener(e -> {
            try {
                // Capturar os valores dos campos
                String nome = nomeField.getText().trim(); // Obtém o nome
                String cpf = cpfField.getText().trim(); // Obtém o CPF
                String endereco = enderecoField.getText().trim(); // Obtém o endereço
                String telefone = telefoneField.getText().trim(); // Obtém o telefone
                String senha = senhaField.getText().trim(); // Obtém a senha
                String status = (String) statusComboBox.getSelectedItem(); // Obtém o status da matrícula

                // Obter o ID da turma selecionada no JComboBox
                String turmaSelecionada = (String) turmaComboBox.getSelectedItem(); // Turma selecionada
                int turmaId = -1; // Inicializa o ID da turma
                if (turmaSelecionada != null) { // Verifica se uma turma foi selecionada
                    turmaId = Integer.parseInt(turmaSelecionada.split("-")[0].trim()); // Extrai o ID da turma
                } else {
                    // Mensagem caso não seja selecionada uma turma válida
                    JOptionPane.showMessageDialog(null, "Selecione uma turma válida.");
                    return; // Sai do método se não houver turma válida
                }

                // Convertendo a data de nascimento
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
                Date dataNascimento = formatter.parse(dataNascimentoField.getText().trim()); // Converte a string para
                                                                                             // Date

                // Criar objeto Aluno com todos os parâmetros necessários
                Aluno aluno = new Aluno(0, nome, cpf, dataNascimento, endereco, telefone, senha, turmaId, status); // Cria
                                                                                                                   // um
                                                                                                                   // novo
                                                                                                                   // aluno

                // Chamar o controlador para criar o aluno
                boolean sucesso = alunoController.criarAluno(aluno); // Chama o método para criar o aluno

                if (sucesso) {
                    // Mensagem de sucesso ao criar o aluno
                    JOptionPane.showMessageDialog(null, "Aluno criado com sucesso!");
                } else {
                    // Mensagem de erro se ocorrer um problema ao criar o aluno
                    JOptionPane.showMessageDialog(null, "Erro ao criar aluno.");
                }
            } catch (Exception ex) {
                // Mensagem de erro em caso de exceção
                JOptionPane.showMessageDialog(null, "Erro ao criar aluno: " + ex.getMessage());
            }
        });

        // Adicionar os campos ao painel
        panel.add(nomeLabel); // Adiciona o rótulo do nome
        panel.add(nomeField); // Adiciona o campo do nome

        panel.add(cpfLabel); // Adiciona o rótulo do CPF
        panel.add(cpfField); // Adiciona o campo do CPF

        panel.add(dataNascimentoLabel); // Adiciona o rótulo da data de nascimento
        panel.add(dataNascimentoField); // Adiciona o campo da data de nascimento

        panel.add(enderecoLabel); // Adiciona o rótulo do endereço
        panel.add(enderecoField); // Adiciona o campo do endereço

        panel.add(telefoneLabel); // Adiciona o rótulo do telefone
        panel.add(telefoneField); // Adiciona o campo do telefone

        panel.add(senhaLabel); // Adiciona o rótulo da senha
        panel.add(senhaField); // Adiciona o campo da senha

        panel.add(turmaLabel); // Adiciona o rótulo da turma
        panel.add(turmaComboBox); // Adiciona o ComboBox da turma

        panel.add(statusMatriculaLabel); // Adiciona o rótulo do status da matrícula
        panel.add(statusComboBox); // Adiciona o ComboBox do status da matrícula

        panel.add(new JLabel()); // Espaçamento
        panel.add(criarAlunoButton); // Adiciona o botão de criar aluno

        return panel; // Retorna o painel criado
    }

    private void loadTurmas(JComboBox<String> turmaComboBox) {
        // Exemplo de como carregar as turmas do banco de dados
        List<Turma> turmas = turmaController.listarTodasTurmas(); // Método que deve retornar a lista de turmas
        for (Turma turma : turmas) {
            turmaComboBox.addItem(turma.getTurmaId() + " - " + turma.getSerie()); // Exibe o ID e a série
        }
    }
}
