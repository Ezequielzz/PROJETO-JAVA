# Sistema de Gestão Escolar

- O **Sistema de Gerenciamento Escolar** visa facilitar a administração de uma escola, oferecendo funcionalidades como o
  cadastro de alunos, professores e turmas, o controle de notas e frequências, além da geração de relatórios para
  acompanhamento do desempenho acadêmico e gestão de matrículas. O sistema também pode incluir um portal para pais e
  alunos, oferecendo acesso a informações relevantes, como boletins e horários de aula.

## Escopo do Sistema

### 1. Cadastro e Gerenciamento de Alunos:

- O sistema deve permitir o cadastro completo dos alunos, contendo informações pessoais como:

    - Nome completo, CPF, data de nascimento, endereço, telefone.
    - Informações acadêmicas: série, turma, status da matrícula (ativo, transferido, desligado).
    - Histórico escolar: disciplinas cursadas e resultados anteriores.

- **Atualização de Dados**: Deve ser possível atualizar dados do aluno, como mudança de endereço ou alteração no status
  da
  matrícula.

- **Transferência e Desligamento**: Função para registrar e documentar transferências para outras escolas ou o
  desligamento
  do aluno.

- **Listagem e Filtragem**: Possibilidade de listar alunos por série, turma, ou filtrar por status (ativo, transferido).

### 2. Cadastro de Professores:

- O sistema permite o cadastro completo de professores, incluindo:

    - Nome completo, CPF, departamento, disciplinas lecionadas.
    - Informações de contato (endereço, telefone, e-mail).

- **Atualização de Informações**: Permitir a atualização de dados, como a mudança de disciplinas lecionadas ou de
  departamento.

- **Histórico de Disciplinas**: Registro das disciplinas que o professor já lecionou e a carga horária correspondente.

### 3. Gerenciamento de Turmas e Disciplinas:

- **Turmas**:

    - Cadastro de turmas, definindo série, ano letivo, turno (matutino, vespertino ou noturno) e sala.
    - Atribuição de alunos a turmas.

- **Disciplinas**:

    - Criação e gerenciamento das disciplinas para cada turma, definindo os professores
      responsáveis.

### 4. Controle Acadêmico:

   - **Notas**:
     - Registro das notas de cada aluno por disciplina e por bimestre.
     - Cálculo automático das médias bimestrais e finais, com base nos critérios definidos pela escola.

   - **Frequência**:
     - Registro diário da frequência dos alunos, considerando presença, faltas justificadas e não justificadas.
     - Relatórios de presença que ajudam a identificar alunos com problemas de assiduidade.

   - **Histórico Acadêmico**: Geração de um histórico acadêmico completo para cada aluno, listando suas notas, disciplinas cursadas e frequências ao longo dos anos.

### 6. Emissão de Boletins e Relatórios:

   - **Boletins**:
     - Geração de boletins escolares, com as notas por disciplina, média geral e situação final (aprovado/reprovado).
     - O boletim pode ser emitido, com formato pronto para ser entregue aos alunos ou enviado por e-mail.

   - Relatórios de Frequência:
     - Relatório detalhado da frequência por aluno ou por turma, com total de faltas, justificativas e alertas para acompanhamento de pais e professores.

   - Relatórios Administrativos:
     - Relatórios sobre o desempenho geral das turmas e séries, permitindo uma análise detalhada do rendimento acadêmico por disciplina e período.

### 7. Interface Gráfica para Interação com o Sistema:

   - **Painel de Controle**: Um painel inicial que permite aos usuários acessar as funcionalidades principais, como cadastro de alunos, lançamentos de notas e emissão de relatórios.

   - **Sistema de Login**: Controle de acesso com diferentes níveis de permissão, garantindo que administradores, professores e alunos tenham acesso adequado às informações e funcionalidades do sistema.
     - Administrador: Acesso total para cadastro e gerenciamento de usuários, turmas e disciplinas.
     - Professor: Permissões para lançar notas, controlar frequência e emitir relatórios de sua turma.
     - Aluno/Pais: Consulta de boletins, notas e relatórios de presença.

   - **Navegação Intuitiva**: A interface gráfica deve ser simples e organizada, com menus claros para fácil navegação entre as diferentes áreas do sistema.

### 8. Gestão de Matrículas:

  -  **Matrícula Inicial**: O sistema permitirá a realização de matrículas de novos alunos, com todos os dados pessoais e acadêmicos necessários.

### 9. Geração de Relatórios Detalhados:

   - **Relatório de Alunos Ativos**: Listagem de todos os alunos ativos, com filtros por série, turma, e status de matrícula.
   - **Relatório de Professores**: Detalhamento dos professores, disciplinas atribuídas e carga horária.
   - **Relatório de Desempenho Acadêmico**: Informações sobre notas e frequência de todos os alunos, com possibilidade de filtrar por turma ou disciplina.
   - **Exportação**: Todos os relatórios devem ser exportáveis em PDF, CSV ou TXT, facilitando o armazenamento e a entrega dos dados aos interessados.

## Diagramas.

### **Diagrama de Classe:**

1. **Pessoa**
  - **Atributos**
    - `id`: Integer (PK)
    - `nome`: String
    - `cpf`: String
    - `dataNascimento`: Date
    - `endereco`: String
    - `telefone`: String

  - **Métodos**
    - `atualizarDados()`
    - `formatarTelefone()`
    - `toString()`

2. **Aluno**
  - **Atributos:**
    - `id`: Integer (PK, FK para Pessoa)
    - `serie`: String
    - `turma`: Turma (FK)
    - `statusMatricula`: String (ativo, transferido, desligado)

  - **Métodos:**
    - `matricular()`
    - `atualizarDados()`@Override
    - `consultarHistorico()`

3. **Professor**
  - **Atributos:**
    - `id`: Integer (PK, FK para Pessoa)
    - `disciplinas`: List<Disciplina>

  - **Métodos:**
    - `atribuirDisciplina()`
    - `consultarTurmas()`
    - `lancarNota()`
    - `atualizarDados()`@Override

4. **Turma**
  - **Atributos:**
    - `id`: Integer (PK)
    - `serie`: String
    - `anoLetivo`: String
    - `turno`: String
    - `sala`: String
    - `alunos`: List<Aluno>
    - `professores`: List<Professor>
    - `disciplinas`: List<Disciplina>

  - **Métodos:**
    - `adicionarAluno()`
    - `removerAluno()`
    - `listarAlunos()`

5. **Disciplina**
  - **Atributos:**
    - `id`: Integer (PK)
    - `nome`: String
    - `professor`: List<Professor> (FK) - Caso seja lecionado por vários Professores
    - `turma`: Turma (FK)
    - `notas`: List<Nota>

  - **Métodos:**
    - `calcularMedia()`
    - `consultarNotas()`

6. **Nota**
  - **Atributos:**
    - `id`: Integer (PK)
    - `disciplina`: Disciplina (FK)
    - `aluno`: Aluno (FK)
    - `valorNota`: Double
    - `data`: Date

  - **Métodos:**
    - `registrarNota()`
    - `atualizarNota()`

7. **Frequência**
  - **Atributos:**
    - `id`: Integer (PK)
    - `aluno`: Aluno (FK)
    - `disciplina`: Disciplina (FK)
    - `data`: Date
    - `presenca`: Boolean

  - **Métodos:**
    - `registrarPresenca()`
    - `consultarFrequencia()`

8. **Relatório**
  - **Atributos:**
    - `id`: Integer (PK)
    - `tipoRelatorio`: String (boletim, frequência, desempenho)
    - `dataGeracao`: Date
    - `dadosRelatorio`: String (contém os dados formatados)

  - **Métodos:**
    - `gerarBoletim()`
    - `gerarRelatorioFrequencia()`
    - `gerarRelatorioDesempenho()`

9. **Matrícula**
  - **Atributos:**
    - `id`: Integer (PK)
    - `aluno`: Aluno (FK)
    - `turma`: Turma (FK)
    - `dataMatricula`: Date
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