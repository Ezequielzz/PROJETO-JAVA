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
        NotaPanel notaPanel = new NotaPanel(turmaController, alunoController, disciplinaController, notaController);
        FrequenciaPanel frequenciaPanel = new FrequenciaPanel(turmaController, alunoController, disciplinaController, frequenciaController);
        DisciplinaPanel disciplinaPanel = new DisciplinaPanel(turmaController, disciplinaController, professorController);
        TurmaPanel turmaPanel = new TurmaPanel(turmaController);
        RelatorioPanel relatorioPanel = new RelatorioPanel(relatorioController, alunoController);

        // Configurações básicas do JFrame
        setTitle("Sistema de Gestão Escolar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel para listar professores (usando ProfessorPanel)
        JPanel listarProfessoresPanel = professorPanel.criarListarProfessoresPanel();
        tabbedPane.addTab("Professores", listarProfessoresPanel);

        // Painel para listar alunos
        JPanel listarAlunosPanel = alunoPanel.criarListarAlunosPanel();
        tabbedPane.addTab("Alunos", listarAlunosPanel);

        // Painel para matrículas
        JPanel matriculaCriacaoPanel = matriculaPanel.criarMatriculaPanel();
        tabbedPane.addTab("Matrículas", matriculaCriacaoPanel);

        // Painel para notas
        JPanel notaCriacaoPanel = notaPanel.criarPainelAtribuirNota();
        tabbedPane.addTab("Notas", notaCriacaoPanel);

        // Painel para frequencia
        JPanel frequenciaCriacaoPanel = frequenciaPanel.criarPainelRegistrarFrequencia();
        tabbedPane.addTab("Frequência", frequenciaCriacaoPanel);

        // Painel para disciplinas
        JPanel disciplinaCriacaoPanel = disciplinaPanel.criarPainelCriarDisciplina();
        tabbedPane.addTab("Disciplinas", disciplinaCriacaoPanel);

        // Painel para turmas
        JPanel turmaCriacaoPanel = turmaPanel.criarPainelCriarTurma();
        tabbedPane.addTab("Turmas", turmaCriacaoPanel);

        // Painel para relatórios
        JPanel relatorioCriacaoPanel = relatorioPanel.criarPainelGerarRelatorio();
        tabbedPane.addTab("Relatório", relatorioCriacaoPanel);

        // Adicionar o tabbedPane ao JFrame
        add(tabbedPane, BorderLayout.CENTER);

        // Exibir a interface
        setVisible(true);
    }
}
