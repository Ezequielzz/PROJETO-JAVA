import com.br.ezequielzz.Model.Nota;
import com.br.ezequielzz.Model.DAOs.NotaDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotaDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private NotaDAO notaDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Configura o comportamento da conexão e dos statements mockados
        when(connection.prepareStatement(any(String.class), anyInt())).thenReturn(preparedStatement);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        notaDAO = new NotaDAO();
    }

    @Test
    void testRegistrarNota() throws SQLException {
        // Cria uma nota de exemplo
        Nota nota = new Nota(1, 2, 2, 9, new Date());

        // Simula o comportamento do executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Simula o retorno de uma chave gerada
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1); // Simula a geração de um ID para a nota

        // Chama o método registrarNota
        notaDAO.registrarNota(nota);

        // Verifica se o executeUpdate foi chamado uma vez
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica se a nota recebeu o ID gerado
        assertEquals(1, nota.getId());
    }

    @Test
    void testAtualizarNota() throws SQLException {
        // Cria uma nota de exemplo com ID
        Nota nota = new Nota(1, 2, 2, 9, new Date());

        // Simula o comportamento do executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Chama o método atualizarNota
        notaDAO.atualizarNota(nota);

        // Verifica se o executeUpdate foi chamado uma vez
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    void testBuscarNotaPorId() throws SQLException {
        // Simula o comportamento do executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Simula o resultado do ResultSet
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("aluno_id")).thenReturn(1);
        when(resultSet.getInt("disciplina_id")).thenReturn(2);
        when(resultSet.getFloat("valor_nota")).thenReturn(8.5f);
        when(resultSet.getDate("data")).thenReturn(new java.sql.Date(new Date().getTime()));

        // Chama o método buscarNotaPorId
        Nota nota = notaDAO.buscarNotaPorId(1);

        // Verifica se a nota retornada não é nula
        assertNotNull(nota);
        assertEquals(1, nota.getId());
    }
}