# Sistema de Gestão Escolar

- O **Sistema de Gerenciamento Escolar** visa facilitar a administração de uma escola, oferecendo funcionalidades como o
  cadastro de alunos, professores e turmas, o controle de notas e frequências, além da geração de relatórios para
  acompanhamento do desempenho acadêmico. O sistema também poderia incluir um portal para pais e
  alunos, oferecendo acesso a informações relevantes, como boletins e horários de aula.

## Escopo do Sistema

### 1. Cadastro e Gerenciamento de Alunos:

- O sistema deve permitir o cadastro completo dos alunos, contendo informações pessoais como:

    - Nome completo, CPF, data de nascimento, endereço, telefone.
    - Informações acadêmicas: série, turma, status da matrícula (ativo, transferido, desligado).

- **Atualização de Dados**: Deve ser possível atualizar dados do aluno, como mudança de endereço ou alteração no status
  da
  matrícula.

- **Transferência e Desligamento**: Função para o desligamento
  do aluno.

- **Listagem e Filtragem**: Possibilidade de listar alunos por turma.

### 2. Cadastro de Professores:

- O sistema permite o cadastro completo de professores, incluindo:

    - Nome completo, CPF, departamento, disciplinas lecionadas.
    - Informações de contato (endereço, telefone, e-mail).

- **Atualização de Informações**: Permitir a atualização de dados, como a mudança de disciplinas lecionadas ou d

### 3. Gerenciamento de Turmas e Disciplinas:

- **Turmas**:

    - Cadastro de turmas, definindo série, ano letivo, turno (matutino, vespertino ou noturno) e sala.
    - Atribuição de alunos a turmas.

- **Disciplinas**:

    - Criação e gerenciamento das disciplinas para cada turma, definindo os professores
      responsáveis.

### 4. Controle Acadêmico:

   - **Notas**:
     - Registro das notas de cada aluno por disciplina.

   - **Frequência**:
     - Registro diário da frequência dos alunos, considerando presença.
     - Relatórios de presença que ajudam a identificar alunos com problemas de assiduidade.

### 6. Emissão de Boletins e Relatórios:

   - **Boletins**:
     - Geração de boletins escolares, com as notas por disciplina.
     - O boletim pode ser emitido, com formato pronto para ser entregue aos alunos ou enviado por e-mail.

   - Relatórios de Frequência:
     - Relatório detalhado da frequência por aluno com total de faltas.

### 7. Interface Gráfica para Interação com o Sistema:

   - **Painel de Controle**: Um painel inicial que permite aos usuários acessar as funcionalidades principais, como cadastro de alunos, lançamentos de notas e emissão de relatórios.

   - **Navegação Intuitiva**: A interface gráfica deve ser simples e organizada, com menus claros para fácil navegação entre as diferentes áreas do sistema.

### 8. Gestão de Matrículas:

  -  **Matrícula Inicial**: O sistema permitirá a realização de matrículas de novos alunos, com todos os dados pessoais e acadêmicos necessários.

### 9. Geração de Relatórios Detalhados:

   - **Relatório de Desempenho Acadêmico**: Informações sobre notas e frequência de todos os alunos.
   - **Exportação**: Todos os relatórios devem ser exportáveis em PDF, CSV ou TXT, facilitando o armazenamento e a entrega dos dados aos interessados.

## Diagramas.

### **Diagrama de Classe:**

1. **Pessoa**
  - **Atributos**
    - `id`: Integer (PK)
    - `nome`: String
    - `cpf`: String
    - `data_nascimento`: Date
    - `endereco`: String
    - `telefone`: String
    - `senha`: String

  - **Métodos**
    - `atualizarDados()`
    - `formatarTelefone()`
    - `formatarCpf()`
    - `toString()`

1. **Aluno**
  - **Atributos:**
    - `id`: Integer (PK, FK para Pessoa)
    - `serie`: String
    - `turma`: Turma (FK)
    - `status_matricula`: String (ativo, transferido, desligado)

  - **Métodos:**
    - `matricular()`
    - `atualizarDados()`@Override
    - `consultarHistorico()`

2. **Professor**
  - **Atributos:**
    - `id`: Integer (PK, FK para Pessoa)
    - `disciplinas`: List<Disciplina>

  - **Métodos:**
    - `atribuirDisciplina()`
    - `consultarTurmas()`
    - `lancarNota()`
    - `atualizarDados()`@Override

3. **Turma**
  - **Atributos:**
    - `id`: Integer (PK)
    - `serie`: String
    - `ano_letivo`: String
    - `turno`: String
    - `sala`: String
    - `alunos`: List<Aluno>
    - `professores`: List<Professor>
    - `disciplinas`: List<Disciplina>

  - **Métodos:**
    - `adicionarAluno()`
    - `removerAluno()`
    - `listarAlunos()`

4. **Disciplina**
  - **Atributos:**
    - `id`: Integer (PK)
    - `nome`: String
    - `professor`: List<Professor> (FK) - Caso seja lecionado por vários Professores
    - `turma`: Turma (FK)
    - `notas`: List<Nota>

  - **Métodos:**
    - `calcularMedia()`
    - `consultarNotas()`

5. **Nota**
  - **Atributos:**
    - `id`: Integer (PK)
    - `disciplina`: Disciplina (FK)
    - `aluno`: Aluno (FK)
    - `valor_nota`: Double
    - `data`: Date

  - **Métodos:**
    - `registrarNota()`
    - `atualizarNota()`

6. **Frequência**
  - **Atributos:**
    - `id`: Integer (PK)
    - `aluno`: Aluno (FK)
    - `disciplina`: Disciplina (FK)
    - `data`: Date
    - `presenca`: Boolean

  - **Métodos:**
    - `registrarPresenca()`
    - `consultarFrequencia()`

7. **Relatório**
  - **Atributos:**
    - `id`: Integer (PK)
    - `tipo_relatorio`: String (boletim, frequência, desempenho)
    - `data_geracao`: Date
    - `dados_relatorio`: String (contém os dados formatados)

  - **Métodos:**
    - `gerarBoletim()`
    - `gerarRelatorioFrequencia()`
    - `gerarRelatorioDesempenho()`

8. **Matrícula**
  - **Atributos:**
    - `id`: Integer (PK)
    - `aluno`: Aluno (FK)
    - `turma`: Turma (FK)
    - `data_matricula`: Date
    - `status`: String (matriculado, cancelado)

  - **Métodos:**
    - `realizarMatricula()`
    - `cancelarMatricula()`

### Diagrama de Uso

  <div align="center">
    <img src="/img/Diagrama de Uso.png">
  </div>

### Diagrama de Fluxo

  <div align="center">
    <img src="/img/Diagrama de Fluxo.png">
  </div>

### Testes Unitários

-------------------------------------------------------------------------------
**Test set: AlunoDAOTest**  
**Tests run:** 3  
**Failures:** 0  
**Errors:** 0  
**Skipped:** 0  
**Time elapsed:** 2.579 s  
**Status:** Sucesso

-------------------------------------------------------------------------------
**Test set: DisciplinaDAOTest**  
**Tests run:** 4  
**Failures:** 4  
**Errors:** 0  
**Skipped:** 0  
**Time elapsed:** 2.075 s  
**Status:** Falha
- **DisciplinaDAOTest.testConsultarNotasAluno**
    - **Tempo decorrido:** 1.688 s
    - **Erro:** `org.opentest4j.AssertionFailedError: expected: <1> but was: <0>`

- **DisciplinaDAOTest.testCriarDisciplina**
    - **Tempo decorrido:** 0.122 s
    - **Erro:** `Wanted but not invoked: preparedStatement.executeUpdate(); Actually, there were zero interactions with this mock.`

- **DisciplinaDAOTest.testBuscarDisciplinaPorId**
    - **Tempo decorrido:** 0.115 s
    - **Erro:** `org.opentest4j.AssertionFailedError: expected: not <null>`

- **DisciplinaDAOTest.testBuscarDisciplinasPorTurma**
    - **Tempo decorrido:** 0.122 s
    - **Erro:** `org.opentest4j.AssertionFailedError: expected: <1> but was: <0>`

-------------------------------------------------------------------------------
**Test set: NotaDAOTest**  
**Tests run:** 3  
**Failures:** 2  
**Errors:** 0  
**Skipped:** 0  
**Time elapsed:** 0.302 s  
**Status:** Falha
- **NotaDAOTest.testRegistrarNota**
    - **Tempo decorrido:** 0.112 s
    - **Erro:** `Wanted but not invoked: preparedStatement.executeUpdate(); Actually, there were zero interactions with this mock.`

- **NotaDAOTest.testAtualizarNota**
    - **Tempo decorrido:** 0.096 s
    - **Erro:** `Wanted but not invoked: preparedStatement.executeUpdate(); Actually, there were zero interactions with this mock.`

---

### Schema Tabela

CREATE TABLE Aluno (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(14) UNIQUE NOT NULL,
data_nascimento DATE NOT NULL,
endereco VARCHAR(200),
telefone VARCHAR(15),
senha VARCHAR(255),
turma_id INT REFERENCES Turma(id),
status_matricula VARCHAR(20) CHECK (status_matricula IN ('ativo', 'transferido', 'desligado'))
);

CREATE TABLE Professor (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(14) UNIQUE NOT NULL,
data_nascimento DATE NOT NULL,
endereco VARCHAR(200),
telefone VARCHAR(15),
senha VARCHAR(255)
);

CREATE TABLE Turma (
id SERIAL PRIMARY KEY,
serie VARCHAR(50),
ano_letivo VARCHAR(9),
turno VARCHAR(20) CHECK (turno IN ('matutino', 'vespertino', 'noturno')),
sala VARCHAR(10)
);

CREATE TABLE Disciplina (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
turma_id INT REFERENCES Turma(id) ON DELETE CASCADE
);

CREATE TABLE Nota (
id SERIAL PRIMARY KEY,
aluno_id INT REFERENCES Aluno(id) ON DELETE CASCADE,
disciplina_id INT REFERENCES Disciplina(id) ON DELETE CASCADE,
valor_nota NUMERIC(5,2),
data DATE NOT NULL
);

CREATE TABLE Frequencia (
id SERIAL PRIMARY KEY,
aluno_id INT REFERENCES Aluno(id) ON DELETE CASCADE,
disciplina_id INT REFERENCES Disciplina(id) ON DELETE CASCADE,
data DATE NOT NULL,
presenca BOOLEAN NOT NULL
);

CREATE TABLE Matricula (
id SERIAL PRIMARY KEY,
aluno_id INT REFERENCES Aluno(id) ON DELETE CASCADE,
turma_id INT REFERENCES Turma(id) ON DELETE CASCADE,
data_matricula DATE NOT NULL,
status VARCHAR(20) CHECK (status IN ('matriculado', 'cancelado', 'pendente'))
);

CREATE TABLE Relatorio (
id SERIAL PRIMARY KEY,
tipo_relatorio VARCHAR(50) CHECK (tipo_relatorio IN ('boletim', 'frequência', 'desempenho')),
data_geracao DATE NOT NULL,
dados_relatorio TEXT
);


-- Adicionando referência do professor na tabela Disciplina
ALTER TABLE Disciplina
ADD COLUMN professor_id INT REFERENCES Professor(id) ON DELETE SET NULL;