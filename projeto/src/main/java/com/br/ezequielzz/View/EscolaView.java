package com.br.ezequielzz.View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.br.ezequielzz.Controller.AlunoController;
import com.br.ezequielzz.Controller.DisciplinaController;
import com.br.ezequielzz.Controller.FrequenciaController;
import com.br.ezequielzz.Controller.MatriculaController;
import com.br.ezequielzz.Controller.NotaController;
import com.br.ezequielzz.Controller.ProfessorController;
import com.br.ezequielzz.Controller.RelatorioController;
import com.br.ezequielzz.Controller.TurmaController;

// Classe principal da interface gráfica do sistema de gestão escolar
public class EscolaView extends JFrame {
    // Declaração dos controladores para gerenciar as diferentes funcionalidades
    private AlunoController alunoController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;
    private NotaController notaController;
    private TurmaController turmaController;
    private FrequenciaController frequenciaController;
    private RelatorioController relatorioController;
    private MatriculaController matriculaController;

    // Construtor da classe EscolaView
    public EscolaView() {
        // Inicialização dos controladores
        alunoController = new AlunoController(); // Controlador para gerenciar alunos
        disciplinaController = new DisciplinaController(); // Controlador para gerenciar disciplinas
        notaController = new NotaController(); // Controlador para gerenciar notas
        turmaController = new TurmaController(); // Controlador para gerenciar turmas
        frequenciaController = new FrequenciaController(); // Controlador para gerenciar frequência
        relatorioController = new RelatorioController(); // Controlador para gerar relatórios
        professorController = new ProfessorController(); // Controlador para gerenciar professores
        matriculaController = new MatriculaController(); // Controlador para gerenciar matrículas

        // Criando as instâncias dos painéis
        ProfessorPanel professorPanel = new ProfessorPanel(professorController); // Painel para gerenciar professores
        AlunoPanel alunoPanel = new AlunoPanel(alunoController, turmaController); // Painel para gerenciar alunos
        MatriculaPanel matriculaPanel = new MatriculaPanel(matriculaController, alunoController, turmaController); // Painel para gerenciar matrículas
        NotaPanel notaPanel = new NotaPanel(turmaController, alunoController, disciplinaController, notaController); // Painel para atribuir notas
        FrequenciaPanel frequenciaPanel = new FrequenciaPanel(turmaController, alunoController, disciplinaController, frequenciaController); // Painel para registrar frequência
        DisciplinaPanel disciplinaPanel = new DisciplinaPanel(turmaController, disciplinaController, professorController); // Painel para gerenciar disciplinas
        TurmaPanel turmaPanel = new TurmaPanel(turmaController); // Painel para gerenciar turmas
        RelatorioPanel relatorioPanel = new RelatorioPanel(relatorioController, alunoController); // Painel para gerar relatórios

        // Configurações básicas do JFrame
        setTitle("Sistema de Gestão Escolar"); // Define o título da janela
        setSize(800, 600); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela
        setLayout(new BorderLayout()); // Define o layout do JFrame como BorderLayout

        // Criação do JTabbedPane para permitir múltiplas abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel para listar professores (usando ProfessorPanel)
        JPanel listarProfessoresPanel = professorPanel.criarListarProfessoresPanel(); // Cria o painel para listar professores
        tabbedPane.addTab("Professores", listarProfessoresPanel); // Adiciona a aba de professores ao JTabbedPane

        // Painel para listar alunos
        JPanel listarAlunosPanel = alunoPanel.criarListarAlunosPanel(); // Cria o painel para listar alunos
        tabbedPane.addTab("Alunos", listarAlunosPanel); // Adiciona a aba de alunos ao JTabbedPane

        // Painel para matrículas
        JPanel matriculaCriacaoPanel = matriculaPanel.criarMatriculaPanel(); // Cria o painel para criar matrículas
        tabbedPane.addTab("Matrículas", matriculaCriacaoPanel); // Adiciona a aba de matrículas ao JTabbedPane

        // Painel para notas
        JPanel notaCriacaoPanel = notaPanel.criarPainelAtribuirNota(); // Cria o painel para atribuir notas
        tabbedPane.addTab("Notas", notaCriacaoPanel); // Adiciona a aba de notas ao JTabbedPane

        // Painel para frequência
        JPanel frequenciaCriacaoPanel = frequenciaPanel.criarPainelRegistrarFrequencia(); // Cria o painel para registrar frequência
        tabbedPane.addTab("Frequência", frequenciaCriacaoPanel); // Adiciona a aba de frequência ao JTabbedPane

        // Painel para disciplinas
        JPanel disciplinaCriacaoPanel = disciplinaPanel.criarPainelCriarDisciplina(); // Cria o painel para criar disciplinas
        tabbedPane.addTab("Disciplinas", disciplinaCriacaoPanel); // Adiciona a aba de disciplinas ao JTabbedPane

        // Painel para turmas
        JPanel turmaCriacaoPanel = turmaPanel.criarPainelCriarTurma(); // Cria o painel para criar turmas
        tabbedPane.addTab("Turmas", turmaCriacaoPanel); // Adiciona a aba de turmas ao JTabbedPane

        // Painel para relatórios
        JPanel relatorioCriacaoPanel = relatorioPanel.criarPainelGerarRelatorio(); // Cria o painel para gerar relatórios
        tabbedPane.addTab("Relatório", relatorioCriacaoPanel); // Adiciona a aba de relatórios ao JTabbedPane

        // Adicionar o tabbedPane ao JFrame
        add(tabbedPane, BorderLayout.CENTER); // Adiciona o JTabbedPane ao centro do JFrame

        // Exibir a interface
        setVisible(true); // Torna a janela visível
    }
}
