import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.Disciplina;
import com.br.ezequielzz.Model.DisciplinaDAO;
import com.br.ezequielzz.Model.Nota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisciplinaDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DisciplinaDAO disciplinaDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(any(String.class), anyInt())).thenReturn(preparedStatement);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        disciplinaDAO = new DisciplinaDAO(); // Aqui você precisa ajustar o construtor se ele aceitar injeção de dependências.
    }

    @Test
    void testCriarDisciplina() throws SQLException {
        // Cria uma disciplina de exemplo
        Disciplina disciplina = new Disciplina(1, "Matemática", 4);

        // Simula o comportamento do executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Simula o retorno de uma chave gerada
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1); // Simula a geração de um ID para a disciplina

        // Chama o método criarDisciplina
        disciplinaDAO.criarDisciplina(disciplina);

        // Verifica se o executeUpdate foi chamado uma vez
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica se a disciplina recebeu o ID gerado
        assertEquals(1, disciplina.getId());
    }

    @Test
    void testBuscarDisciplinasPorTurma() throws SQLException {
        // Simula o comportamento do executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Simula o resultado do ResultSet
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Simula uma única linha no resultado
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nome")).thenReturn("Matemática");
        when(resultSet.getInt("turma_id")).thenReturn(4);
        when(resultSet.getInt("professor_id")).thenReturn(1);

        // Chama o método buscarDisciplinasPorTurma
        List<Disciplina> disciplinas = disciplinaDAO.buscarDisciplinasPorTurma(1);

        // Verifica se a lista retornada tem uma disciplina
        assertNotNull(disciplinas);
        assertEquals(1, disciplinas.size());
        assertEquals("Matemática", disciplinas.get(0).getNome());
    }

    @Test
    void testConsultarNotasAluno() throws SQLException {
        // Cria objetos de exemplo
        Disciplina disciplina = new Disciplina(1, "Matemática", 4);
        Aluno aluno = new Aluno(1, "João", "12345678955", new Date(), "Rua A", "123456", "senha123", 4, "ativo");

        // Simula o comportamento do executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Simula o resultado do ResultSet
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Simula uma única linha no resultado
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("aluno_id")).thenReturn(1);
        when(resultSet.getInt("disciplina_id")).thenReturn(1);
        when(resultSet.getFloat("valor_nota")).thenReturn(9.5f);
        when(resultSet.getDate("data")).thenReturn(new java.sql.Date(System.currentTimeMillis()));

        // Chama o método consultarNotasAluno
        List<Nota> notas = disciplinaDAO.consultarNotasAluno(disciplina, aluno);

        // Verifica se a lista retornada tem uma nota
        assertNotNull(notas);
        assertEquals(1, notas.size());
        assertEquals(9.5f, notas.get(0).getValorNota());
    }

    @Test
    void testBuscarDisciplinaPorId() throws SQLException {
        // Simula o comportamento do executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Simula o resultado do ResultSet
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nome")).thenReturn("Matemática");
        when(resultSet.getInt("turma_id")).thenReturn(4);
        when(resultSet.getInt("professor_id")).thenReturn(1);

        // Chama o método buscarDisciplinaPorId
        Disciplina disciplina = disciplinaDAO.buscarDisciplinaPorId(1);

        // Verifica se a disciplina retornada não é nula e se os valores são os esperados
        assertNotNull(disciplina);
        assertEquals(1, disciplina.getId());
        assertEquals("Matemática", disciplina.getNome());
    }
}