//import com.br.ezequielzz.Model.Aluno;
//import com.br.ezequielzz.Model.AlunoDAO;
//import com.br.ezequielzz.Model.Database.ConnectionFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AlunoDAOTest {
//
//    @Mock
//    private Connection connection;
//
//    @Mock
//    private PreparedStatement preparedStatement;
//
//    @Mock
//    private ResultSet resultSet;
//
//    private AlunoDAO alunoDAO;
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        MockitoAnnotations.openMocks(this);
//
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//
//        // Inicializa o AlunoDAO com a conexão mockada
//        alunoDAO = new AlunoDAO();
//        alunoDAO.setConnection(connection); // Passando a conexão mockada
//    }
//
//    @Test
//    void testCriarAluno() throws SQLException {
//        Aluno aluno = new Aluno(1, "João", "12345678955", new Date(), "Rua A", "123456", "senha123", 4, "ativo");
//
//        alunoDAO.criarAluno(aluno);
//
//        verify(preparedStatement, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testListarTodos() throws SQLException {
//        // Simula os valores do ResultSet
//        when(resultSet.next()).thenReturn(true, false); // Primeiro true indica que há um registro, depois false
//        when(resultSet.getInt("id")).thenReturn(13);
//        when(resultSet.getString("nome")).thenReturn("João");
//        when(resultSet.getString("cpf")).thenReturn("12345678944");
//        when(resultSet.getDate("data_nascimento")).thenReturn(new java.sql.Date(new Date().getTime()));
//        when(resultSet.getString("endereco")).thenReturn("Rua A");
//        when(resultSet.getString("telefone")).thenReturn("123456");
//        when(resultSet.getString("senha")).thenReturn("senha123");
//        when(resultSet.getInt("turma_id")).thenReturn(4);
//        when(resultSet.getString("status_matricula")).thenReturn("ativo");
//
//        // Chama o método que estamos testando
//        List<Aluno> alunos = alunoDAO.listarTodos();
//
//        // Verifica se o método retornou 1 aluno
//        assertEquals(1, alunos.size());
//        assertEquals("João", alunos.get(0).getNome());
//    }
//
//    @Test
//    void testListarAlunosPorTurma() throws SQLException {
//        // Simula os valores do ResultSet para alunos de uma turma
//        when(preparedStatement.executeQuery()).thenReturn(resultSet); // Isso é importante
//
//        when(resultSet.next()).thenReturn(true, false); // Um registro
//        when(resultSet.getInt("id")).thenReturn(1);
//        when(resultSet.getString("nome")).thenReturn("João");
//
//        // Chama o método de listar alunos por turma
//        List<Aluno> alunos = alunoDAO.listarAlunosPorTurma(1);
//
//        // Verifica se foi retornado o aluno esperado
//        assertEquals(1, alunos.size());
//        assertEquals("João", alunos.get(0).getNome());
//    }
//}
