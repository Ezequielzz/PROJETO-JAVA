import com.br.ezequielzz.Model.Aluno;
import com.br.ezequielzz.Model.AlunoDAO;
import com.br.ezequielzz.Model.Database.ConnectionFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Inicializa os mocks automaticamente no JUnit 5
public class AlunoDAOTest {

    @InjectMocks
    private AlunoDAO alunoDAO;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    private MockedStatic<ConnectionFactory> mockedStaticConnectionFactory;

    @BeforeEach
    public void setUp() throws Exception {
        // Simulando o método estático
        mockedStaticConnectionFactory = mockStatic(ConnectionFactory.class);
        mockedStaticConnectionFactory.when(ConnectionFactory::getConnection).thenReturn(mockConnection);
    }

    @Test
    public void testCriarAluno() throws Exception {
        // Simulando um Aluno para o teste
        Aluno aluno = new Aluno(1, "João", "123456789", new Date(), "Rua X", "999999999", "senha123", 101, "ativo");

        // Simulando o comportamento do PreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Chamando o método que estamos testando
        alunoDAO.criarAluno(aluno);

        // Verificando interações e garantindo que o método foi chamado com os valores corretos
        verify(mockPreparedStatement).setString(1, aluno.getNome());
        verify(mockPreparedStatement).setString(2, aluno.getCpf());
        verify(mockPreparedStatement).setDate(3, any(java.sql.Date.class));
        verify(mockPreparedStatement).setString(4, aluno.getEndereco());
        verify(mockPreparedStatement).setString(5, aluno.getTelefone());
        verify(mockPreparedStatement).setString(6, aluno.getSenha());
        verify(mockPreparedStatement).setInt(7, aluno.getTurmaId());
        verify(mockPreparedStatement).setString(8, aluno.getStatusMatricula());

        // Verifica se executeUpdate foi chamado
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testCriarAlunoThrowsSQLException() throws Exception {
        // Simulando exceção de SQL
        Aluno aluno = new Aluno(1, "João", "123456789", new Date(), "Rua X", "999999999", "senha123", 101, "ativo");

        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Erro ao inserir aluno"));

        // Verificando se a exceção SQL é lançada
        assertThrows(SQLException.class, () -> alunoDAO.criarAluno(aluno));
    }
}
