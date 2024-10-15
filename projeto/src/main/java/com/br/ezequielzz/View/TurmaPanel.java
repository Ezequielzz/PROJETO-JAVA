package com.br.ezequielzz.View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.br.ezequielzz.Controller.TurmaController;
import com.br.ezequielzz.Model.Turma;

// Classe responsável pela interface gráfica para criar turmas
public class TurmaPanel {
    // Controlador responsável pela lógica de criação de turmas
    private TurmaController turmaController;

    // Construtor da classe TurmaPanel que recebe o controlador como parâmetro
    public TurmaPanel(TurmaController turmaController) {
        this.turmaController = turmaController; // Inicializa o controlador de turma
    }
    
    // Método que cria e retorna um painel para criar novas turmas
    public JPanel criarPainelCriarTurma() {
        // Criação do painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Define o layout do painel como vertical

        // Label e campo de texto para série
        JLabel labelSerie = new JLabel("Série:"); // Rótulo para entrada da série
        JTextField campoSerie = new JTextField(20); // Campo de texto para inserir a série
        painel.add(labelSerie); // Adiciona o rótulo ao painel
        painel.add(campoSerie); // Adiciona o campo de texto ao painel

        // Label e campo de texto para ano letivo
        JLabel labelAnoLetivo = new JLabel("Ano Letivo:"); // Rótulo para entrada do ano letivo
        JTextField campoAnoLetivo = new JTextField(20); // Campo de texto para inserir o ano letivo
        painel.add(labelAnoLetivo); // Adiciona o rótulo ao painel
        painel.add(campoAnoLetivo); // Adiciona o campo de texto ao painel

        // Label e JComboBox para turno
        JLabel labelTurno = new JLabel("Turno:"); // Rótulo para seleção do turno
        JComboBox<String> comboTurno = new JComboBox<>(new String[] { "matutino", "vespertino", "noturno" }); // ComboBox para selecionar o turno
        painel.add(labelTurno); // Adiciona o rótulo ao painel
        painel.add(comboTurno); // Adiciona a ComboBox ao painel

        // Label e campo de texto para sala
        JLabel labelSala = new JLabel("Sala:"); // Rótulo para entrada da sala
        JTextField campoSala = new JTextField(20); // Campo de texto para inserir a sala
        painel.add(labelSala); // Adiciona o rótulo ao painel
        painel.add(campoSala); // Adiciona o campo de texto ao painel

        // Botão para criar turma
        JButton botaoCriarTurma = new JButton("Criar Turma"); // Botão para criar a turma
        painel.add(botaoCriarTurma); // Adiciona o botão ao painel

        // Ação do botão para criar a turma
        botaoCriarTurma.addActionListener(e -> { // Adiciona um ouvinte de ação ao botão
            String serie = campoSerie.getText(); // Obtém o texto do campo série
            String anoLetivo = campoAnoLetivo.getText(); // Obtém o texto do campo ano letivo
            String turno = (String) comboTurno.getSelectedItem(); // Obtém o turno selecionado
            String sala = campoSala.getText(); // Obtém o texto do campo sala

            // Validação do ano letivo
            if (serie.isEmpty() || anoLetivo.isEmpty() || turno == null || sala.isEmpty()) { // Verifica se algum campo está vazio
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos."); // Mensagem de erro
            } else {
                try {
                    int ano = Integer.parseInt(anoLetivo); // Tenta converter o ano letivo para um inteiro
                    if (ano < 2024 || ano > 2027) { // Verifica se o ano está dentro de um intervalo válido
                        JOptionPane.showMessageDialog(null, "Ano letivo inválido. Deve estar entre 2024 e 2027."); // Mensagem de erro
                        return; // Sai do método se o ano for inválido
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ano letivo deve ser um número válido."); // Mensagem de erro se a conversão falhar
                    return; // Sai do método se o ano não for um número válido
                }

                // Criação da nova turma e chamada do controlador para criar a turma
                Turma turma = new Turma(0, serie, anoLetivo, turno, sala); // Cria uma nova instância de Turma
                turmaController.criarTurma(turma); // Chama o método do controlador para criar a turma
                JOptionPane.showMessageDialog(null, "Turma criada com sucesso!"); // Mensagem de sucesso
            }
        });

        return painel; // Retorna o painel criado
    }
}
