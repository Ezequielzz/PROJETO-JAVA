package com.br.ezequielzz.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.br.ezequielzz.Controller.ProfessorController;
import com.br.ezequielzz.Model.Professor;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfessorPanel {

    private ProfessorController professorController; // Controlador para gerenciar operações relacionadas a professores
    private JTable tabelaProfessores; // Tabela para exibir a lista de professores

    // Declarar os campos como atributos da classe por conta das Máscaras
    private JTextField nomeField; // Campo de texto para o nome do professor
    private JFormattedTextField cpfField; // Campo formatado para CPF do professor
    private JFormattedTextField dataNascimentoField; // Campo formatado para a data de nascimento
    private JTextField enderecoField; // Campo de texto para o endereço do professor
    private JFormattedTextField telefoneField; // Campo formatado para o telefone do professor
    private JTextField senhaField; // Campo de senha para o professor

    // Construtor da classe que recebe um controlador de professores
    public ProfessorPanel(ProfessorController professorController) {
        this.professorController = professorController; // Inicializa o controlador
    }

    // Método para criar o painel de criação de professores com máscaras
    public JPanel criarProfessorPanel() {
        JPanel panel = new JPanel(); // Cria um painel
        panel.setLayout(new GridLayout(10, 2)); // Define o layout do painel como uma grade

        // Inicializar os campos de texto
        JLabel nomeLabel = new JLabel("Nome:"); // Rótulo para o nome
        nomeField = new JTextField(); // Campo de texto para o nome do professor

        JLabel cpfLabel = new JLabel("CPF:"); // Rótulo para CPF
        cpfField = null; // Inicializa o campo de CPF

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento (dd/MM/yyyy):"); // Rótulo para data de nascimento
        dataNascimentoField = null; // Inicializa o campo de data de nascimento

        JLabel enderecoLabel = new JLabel("Endereço:"); // Rótulo para endereço
        enderecoField = new JTextField(); // Campo de texto para o endereço do professor

        JLabel telefoneLabel = new JLabel("Telefone:"); // Rótulo para telefone
        telefoneField = null; // Inicializa o campo de telefone

        // Inicialização dos campos formatados
        try {
            // Máscara para CPF
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado
            cpfField = new JFormattedTextField(cpfMask); // Campo formatado para CPF

            // Máscara para Data de Nascimento
            MaskFormatter dataNascimentoMask = new MaskFormatter("##/##/####");
            dataNascimentoMask.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado
            dataNascimentoField = new JFormattedTextField(dataNascimentoMask); // Campo formatado para data de nascimento

            // Máscara para Telefone
            MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");
            telefoneMask.setPlaceholderCharacter('_'); // Define o caractere de espaço reservado
            telefoneField = new JFormattedTextField(telefoneMask); // Campo formatado para telefone

        } catch (ParseException e) {
            e.printStackTrace(); // Trata exceção de formato
        }

        JLabel senhaLabel = new JLabel("Senha:"); // Rótulo para senha
        senhaField = new JTextField(); // Campo para a senha do professor

        JButton criarProfessorButton = new JButton("Criar Professor"); // Botão para criar um novo professor

        // Adicionando o ActionListener ao botão
        criarProfessorButton.addActionListener(e -> {
            try {
                // Capturar os valores dos campos
                String nome = nomeField.getText().trim(); // Nome do professor
                String cpf = cpfField.getText().trim(); // CPF do professor
                String endereco = enderecoField.getText().trim(); // Endereço do professor
                String telefone = telefoneField.getText().trim(); // Telefone do professor
                String senha = senhaField.getText().trim(); // Senha do professor

                // Verificação de Data de Nascimento
                String dataNascimentoStr = dataNascimentoField.getText().trim();
                String[] dataSplit = dataNascimentoStr.split("/"); // Separando o dia, mês e ano

                // Se for obtido 3 '/' então separe
                if (dataSplit.length == 3) {
                    int dia = Integer.parseInt(dataSplit[0]); // Obtendo a primeira separação
                    int mes = Integer.parseInt(dataSplit[1]); // Obtendo a segunda separação
                    int ano = Integer.parseInt(dataSplit[2]); // Obtendo a terceira separação

                    // Verificando se o dia, mês e ano são válidos
                    if (dia < 1 || dia > 31) {
                        JOptionPane.showMessageDialog(null, "Dia inválido!");
                        return; // Sai do método se não houver o dia válido
                    }

                    if (mes < 1 || mes > 12) {
                        JOptionPane.showMessageDialog(null, "Mês inválido!");
                        return; // Sai do método se não houver o mês válido
                    }

                    // Buscando o ano do sistema
                    int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
                    if (ano < 1985 || ano > anoAtual) {
                        JOptionPane.showMessageDialog(null, "Ano inválido!");
                        return; // Sai do método se não houver o ano válido
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data de nascimento inválida!");
                    return; // Sai do método se não obtiver o formato correto
                }

                // Convertendo a data de nascimento
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
                Date dataNascimento = formatter.parse(dataNascimentoField.getText().trim()); // Data de nascimento do professor

                // Criar objeto Professor com todos os parâmetros necessários
                Professor professor = new Professor(0, nome, cpf, dataNascimento, endereco, telefone, senha);

                // Chamar o controlador para criar o professor
                boolean sucesso = professorController.criarProfessor(professor); // Tenta criar o professor

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Professor criado com sucesso!"); // Mensagem de sucesso
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao criar professor."); // Mensagem de erro
                }
            } catch (Exception ex) {
                // Captura e exibe erros durante a criação do professor
                JOptionPane.showMessageDialog(null, "Erro ao criar professor: " + ex.getMessage());
            }
        });

        JButton voltarBtn = new JButton("Voltar");
        voltarBtn.addActionListener(e -> {
            panel.setVisible(false); // Esconder o formulário de criação
            tabelaProfessores.setVisible(true); // Mostrar a tabela de alunos novamente
        });

        // Adicionar os campos ao painel
        panel.add(nomeLabel); // Adiciona rótulo de nome
        panel.add(nomeField); // Adiciona campo de nome

        panel.add(cpfLabel); // Adiciona rótulo de CPF
        panel.add(cpfField); // Adiciona campo de CPF

        panel.add(dataNascimentoLabel); // Adiciona rótulo de data de nascimento
        panel.add(dataNascimentoField); // Adiciona campo de data de nascimento

        panel.add(enderecoLabel); // Adiciona rótulo de endereço
        panel.add(enderecoField); // Adiciona campo de endereço

        panel.add(telefoneLabel); // Adiciona rótulo de telefone
        panel.add(telefoneField); // Adiciona campo de telefone

        panel.add(senhaLabel); // Adiciona rótulo de senha
        panel.add(senhaField); // Adiciona campo de senha

        panel.add(new JLabel()); // Espaçamento
        panel.add(criarProfessorButton); // Adiciona botão de criação
        panel.add(voltarBtn); // Adiciona botão de voltar

        return panel; // Retorna o painel criado
    }

    public JPanel criarListarProfessoresPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Usa GridBagLayout para flexibilidade
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
    
        // Definir os nomes das colunas
        String[] colunas = { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone" }; // Colunas da tabela
    
        // Criar um DefaultTableModel inicialmente com dados vazios
        DefaultTableModel modeloTabela = new DefaultTableModel(new Object[0][6], colunas);
        tabelaProfessores = new JTable(modeloTabela); // Inicializa a tabela com o modelo
    
        JScrollPane scrollPane = new JScrollPane(tabelaProfessores); // Cria uma barra de rolagem para a tabela
    
        // Inicializar a tabela com os dados
        atualizarTabelaProfessores(); // Atualiza a tabela com os dados dos professores
    
        // Criar painel para os botões
        JPanel botaoPanel = new JPanel();
        JButton btnCriarNovoProfessor = new JButton("Criar Novo Professor");
        JPanel criarProfessorPanel = criarProfessorPanel(); // Reutiliza o método para o formulário de criação
        criarProfessorPanel.setVisible(false); // Esconder o formulário inicialmente
    
        btnCriarNovoProfessor.addActionListener(e -> {
            tabelaProfessores.setVisible(false); // Esconder a tabela de professores
            criarProfessorPanel.setVisible(true); // Mostrar o painel de criação de professor
        });
    
        // Adicionar o botão ao painel de botões na parte superior
        botaoPanel.add(btnCriarNovoProfessor);
    
        // Botão de Excluir
        JButton btnExcluir = new JButton("Excluir"); // Botão para excluir um professor
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaProfessores.getSelectedRow(); // Obtém a linha selecionada
            if (linhaSelecionada != -1) { // Verifica se uma linha está selecionada
                int professorId = (int) tabelaProfessores.getValueAt(linhaSelecionada, 0); // ID do professor
                try {
                    professorController.excluirProfessor(professorId); // Chama o método para excluir o professor
                    JOptionPane.showMessageDialog(panel, "Professor excluído com sucesso!"); // Mensagem de sucesso
                    atualizarTabelaProfessores(); // Atualizar a tabela após exclusão
                } catch (SQLException ex) {
                    // Mensagem de erro se ocorrer um problema ao excluir
                    JOptionPane.showMessageDialog(panel, "Erro ao excluir professor: " + ex.getMessage());
                }
            } else {
                // Mensagem para informar que deve-se selecionar um professor
                JOptionPane.showMessageDialog(panel, "Selecione um professor para excluir.");
            }
        });
    
        // Botão de Atualizar
        JButton btnAtualizar = new JButton("Atualizar"); // Botão para atualizar um professor
        btnAtualizar.addActionListener(e -> {
            int linhaSelecionada = tabelaProfessores.getSelectedRow(); // Obtém a linha selecionada
            if (linhaSelecionada != -1) { // Verifica se uma linha está selecionada
                // Obter o ID do professor selecionado
                int professorId = (int) tabelaProfessores.getValueAt(linhaSelecionada, 0);
                try {
                    // Buscar os dados completos do professor
                    Professor professor = professorController.buscarProfessorPorId(professorId);
                    // Abrir um diálogo para edição
                    if (professor != null) { // Verifica se o professor foi encontrado
                        editarProfessorDialog(professor); // Chama o método para editar o professor
                    } else {
                        // Mensagem caso o professor não seja encontrado
                        JOptionPane.showMessageDialog(panel, "Professor não encontrado.");
                    }
                } catch (SQLException ex) {
                    // Mensagem de erro se ocorrer um problema ao buscar o professor
                    JOptionPane.showMessageDialog(panel, "Erro ao buscar professor: " + ex.getMessage());
                }
            } else {
                // Mensagem para informar que deve-se selecionar um professor
                JOptionPane.showMessageDialog(panel, "Selecione um professor para atualizar.");
            }
        });
    
        botaoPanel.add(btnAtualizar);
        botaoPanel.add(btnExcluir);
    
        // Layout para os botões no topo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05; // Menor peso para os botões
        panel.add(botaoPanel, gbc);
    
        // Layout para a tabela
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.25; // Tamanho maior para a tabela
        panel.add(scrollPane, gbc);
    
        // Layout para o formulário de criação de professor
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.45; // Espaço menor para o formulário
        panel.add(criarProfessorPanel, gbc);
    
        return panel;
    }

    // Método para atualizar a tabela de professores
    private void atualizarTabelaProfessores() {
        List<Professor> professores = professorController.listarTodosProfessores(); // Lista todos os professores
        Object[][] dadosAtualizados = new Object[professores.size()][6]; // Cria uma matriz para os dados da tabela

        // Preenche a matriz com os dados dos professores
        for (int i = 0; i < professores.size(); i++) {
            Professor professor = professores.get(i); // Obtém o professor atual
            dadosAtualizados[i][0] = professor.getId(); // ID do professor
            dadosAtualizados[i][1] = professor.getNome(); // Nome do professor
            dadosAtualizados[i][2] = professor.getCpf(); // CPF do professor
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
            dadosAtualizados[i][3] = sdf.format(professor.getDataNascimento()); // Data de nascimento formatada
            dadosAtualizados[i][4] = professor.getEndereco(); // Endereço do professor
            dadosAtualizados[i][5] = professor.getTelefone(); // Telefone do professor
        }

        // Atualizar os dados na tabela
        DefaultTableModel model = (DefaultTableModel) tabelaProfessores.getModel(); // Obtém o modelo da tabela
        model.setDataVector(dadosAtualizados, // Atualiza os dados e colunas
                new String[] { "ID", "Nome", "CPF", "Data de Nascimento", "Endereço", "Telefone" });
    }

    // Diálogo de edição de professor
    private void editarProfessorDialog(Professor professor) {
        // Criar um diálogo para editar os dados
        JDialog dialog = new JDialog(); // Cria um diálogo
        dialog.setTitle("Editar Professor"); // Define o título do diálogo
        dialog.setSize(400, 300); // Define o tamanho do diálogo
        dialog.setLayout(new GridLayout(0, 2)); // Define o layout do diálogo como uma grade

        // Campos de texto para editar os dados do professor
        JTextField nomeField = new JTextField(professor.getNome()); // Campo para o nome
        JTextField cpfField = new JTextField(professor.getCpf()); // Campo para o CPF
        JTextField enderecoField = new JTextField(professor.getEndereco()); // Campo para o endereço
        JTextField telefoneField = new JTextField(professor.getTelefone()); // Campo para o telefone

        // Adicionar os campos ao diálogo
        dialog.add(new JLabel("Nome:")); // Rótulo para o nome
        dialog.add(nomeField); // Campo para o nome
        dialog.add(new JLabel("CPF:")); // Rótulo para o CPF
        dialog.add(cpfField); // Campo para o CPF
        dialog.add(new JLabel("Endereço:")); // Rótulo para o endereço
        dialog.add(enderecoField); // Campo para o endereço
        dialog.add(new JLabel("Telefone:")); // Rótulo para o telefone
        dialog.add(telefoneField); // Campo para o telefone

        // Botão de salvar as alterações
        JButton salvarBtn = new JButton("Salvar"); // Botão para salvar as alterações
        salvarBtn.addActionListener(e -> {
            // Atualizar os dados do professor com os valores do formulário
            professor.setNome(nomeField.getText()); // Atualiza o nome
            professor.setCpf(cpfField.getText()); // Atualiza o CPF
            professor.setEndereco(enderecoField.getText()); // Atualiza o endereço
            professor.setTelefone(telefoneField.getText()); // Atualiza o telefone

            // Chamar o método de atualização no controller
            try {
                professorController.atualizarProfessor(professor); // Tenta atualizar o professor
                JOptionPane.showMessageDialog(dialog, "Professor atualizado com sucesso!"); // Mensagem de sucesso
                dialog.dispose(); // Fecha o diálogo
                atualizarTabelaProfessores(); // Atualiza a tabela após a edição
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao atualizar professor: " + ex.getMessage()); // Mensagem de erro
            }
        });

        dialog.add(new JLabel()); // Espaçamento
        dialog.add(salvarBtn); // Adiciona o botão de salvar ao diálogo

        dialog.setVisible(true); // Torna o diálogo visível
    }
}
