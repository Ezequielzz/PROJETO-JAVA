# Manual de Uso do Sistema de Gerenciamento Escolar

## Índice

1. [Introdução](#introdução)
2. [Requisitos](#requisitos)
3. [Interface Gráfica](#interface-gráfica)
   - [Painel de Criação de Disciplina](#painel-de-criação-de-disciplina)
   - [Painel de Criação de Turma](#painel-de-criação-de-turma)
   - [Painel de Matrícula](#painel-de-matrícula)
   - [Painel de Atribuição de Notas](#painel-de-atribuição-de-notas)
   - [Painel de Registro de Frequência](#painel-de-registro-de-frequência)
   - [Painel de Geração de Relatórios](#painel-de-geração-de-relatórios)
   - [Painel de Criação de Aluno](#painel-de-criação-de-aluno)
   - [Painel de Listagem de Alunos](#painel-de-listagem-de-alunos)
   - [Painel de Criação de Professor](#painel-de-criação-de-professor)
   - [Painel de Listagem de Professores](#painel-de-listagem-de-professores)
4. [Funcionalidades](#funcionalidades)
   - [Criar Disciplina](#criar-disciplina)
   - [Criar Turma](#criar-turma)
   - [Realizar Matrícula](#realizar-matrícula)
   - [Lançar Nota](#lançar-nota)
   - [Registrar Frequência](#registrar-frequência)
   - [Gerar Relatórios](#gerar-relatórios)
   - [Criar Aluno](#criar-aluno)
   - [Listar Alunos](#listar-alunos)
   - [Criar Professor](#criar-professor)
   - [Listar Professores](#listar-professores)
5. [Mensagens de Erro e Validações](#mensagens-de-erro-e-validações)
6. [Conclusão](#conclusão)

---

## Introdução

Este manual descreve como utilizar o sistema de gerenciamento escolar. O sistema permite a criação de turmas, disciplinas, atribuição de professores e alunos, além de gerar relatórios como boletins e relatórios de frequência.

## Requisitos

- Java 8 ou superior
- Biblioteca Swing para interface gráfica
- Sistema de gerenciamento de banco de dados PostgreSQL
- Maven para gerenciamento de dependências
- JUnit e Mockito para testes unitários

## Interface Gráfica

### Painel de Criação de Disciplina

Neste painel, você poderá criar novas disciplinas associando-as a uma turma e a um professor. 

- **Campos disponíveis:**
  - Nome da Disciplina (campo de texto)
  - Seleção de Turma (via JComboBox)
  - Seleção de Professor (via JComboBox)
  
- **Botão disponível:**
  - "Criar Disciplina"

### Painel de Criação de Turma

Neste painel, o usuário poderá cadastrar novas turmas no sistema.

- **Campos disponíveis:**
  - Série (campo de texto)
  - Ano Letivo (campo de texto)
  - Turno (campo de texto)
  - Sala (campo de texto)

- **Botão disponível:**
  - "Criar Turma"

### Painel de Matrícula

Neste painel, você poderá realizar a matrícula de alunos nas turmas.

- **Campos disponíveis:**
  - Seleção de Aluno (via JComboBox)
  - Seleção de Turma (via JComboBox)
  - Data de Matrícula (campo de texto)
  - Status da Matrícula (via JComboBox)

- **Botão disponível:**
  - "Realizar Matrícula"

### Painel de Atribuição de Notas

Neste painel, você poderá lançar notas para os alunos.

- **Campos disponíveis:**
  - Seleção de Turma (via JComboBox)
  - Seleção de Aluno (via JComboBox)
  - Seleção de Disciplina (via JComboBox)
  - Nota (campo de texto)

- **Botão disponível:**
  - "Lançar Nota"

### Painel de Registro de Frequência

Neste painel, você poderá registrar a presença dos alunos.

- **Campos disponíveis:**
  - Seleção de Turma (via JComboBox)
  - Seleção de Aluno (via JComboBox)
  - Seleção de Disciplina (via JComboBox)
  - Presença (via JComboBox)

- **Botão disponível:**
  - "Registrar Frequência"

### Painel de Geração de Relatórios

Este painel permite a geração de relatórios de desempenho dos alunos, como boletins e relatórios de frequência.

- **Seleção disponível:**
  - Aluno (via JComboBox)

- **Botões disponíveis:**
  - "Gerar Boletim"
  - "Gerar Relatório de Frequência"

### Painel de Criação de Aluno

Neste painel, você poderá criar novos alunos, preenchendo suas informações.

- **Campos disponíveis:**
  - Nome (campo de texto)
  - CPF (campo de texto)
  - Data de Nascimento (campo de texto)
  - Endereço (campo de texto)
  - Telefone (campo de texto)
  - Senha (campo de texto)
  - Seleção de Turma (via JComboBox)
  - Status da Matrícula (campo de texto)

- **Botão disponível:**
  - "Criar Aluno"

### Painel de Listagem de Alunos

Este painel permite visualizar todos os alunos cadastrados, com a opção de editar ou excluir registros.

- **Tabela de Alunos:** 
  - Mostra informações como ID, Nome, CPF, Data de Nascimento, Endereço, Telefone, Turma e Situação de Matrícula.
  
- **Botões disponíveis:**
  - "Atualizar" (para editar um aluno selecionado)
  - "Excluir" (para remover um aluno selecionado)

## Painel de Criação de Professor
Neste painel, você poderá criar novos professores, preenchendo suas informações.

- **Campos disponíveis:**
  - Nome (campo de texto)
  - CPF (campo de texto)
  - Data de Nascimento (campo de texto)
  - Endereço (campo de texto)
  - Telefone (campo de texto)
  - Senha (campo de texto)
- **Botão disponível:**
  - "Criar Professor"

## Painel de Listagem de Professores
Este painel permite visualizar todos os professores cadastrados, com a opção de editar ou excluir registros.

- **Tabela de Professores:**
  - Mostra informações como ID, Nome, CPF, Data de Nascimento, Endereço e Telefone.

- **Botões disponíveis:**
   - "Atualizar" (para editar um professor selecionado)
   - "Excluir" (para remover um professor selecionado)

## Funcionalidades

### Criar Disciplina

1. **Acesso:** Na interface gráfica, acesse o painel de criação de disciplina.
2. **Preenchimento:** Insira o nome da disciplina no campo correspondente, selecione a turma e o professor.
3. **Criação:** Clique no botão "Criar Disciplina" para salvar a disciplina no banco de dados.
4. **Confirmação:** Uma mensagem de sucesso será exibida caso a disciplina seja criada corretamente.

### Criar Turma

1. **Acesso:** Na interface gráfica, acesse o painel de criação de turma.
2. **Preenchimento:** Preencha os campos de série, ano letivo, turno e sala.
3. **Criação:** Clique no botão "Criar Turma".
4. **Confirmação:** Uma mensagem de sucesso será exibida confirmando a criação da turma.

### Realizar Matrícula

1. **Acesso:** Na interface gráfica, acesse o painel de matrícula.
2. **Preenchimento:** Selecione o aluno e a turma desejados. Preencha a data de matrícula e selecione o status da matrícula.
3. **Realização:** Clique no botão "Realizar Matrícula".
4. **Confirmação:** Uma mensagem de sucesso será exibida caso a matrícula seja realizada corretamente.

### Lançar Nota

1. **Acesso:** Na interface gráfica, acesse o painel de atribuição de notas.
2. **Preenchimento:** Selecione a turma, o aluno e a disciplina desejados. Informe a nota no campo apropriado.
3. **Lançamento:** Clique no botão "Lançar Nota".
4. **Confirmação:** Uma mensagem de sucesso será exibida caso a nota seja lançada corretamente.

### Registrar Frequência

1. **Acesso:** Na interface gráfica, acesse o painel de registro de frequência.
2. **Preenchimento:** Selecione a turma, o aluno e a disciplina. Escolha a opção de presença (Presente ou Ausente).
3. **Registro:** Clique no botão "Registrar Frequência".
4. **Confirmação:** Uma mensagem de sucesso será exibida caso a frequência seja registrada corretamente.

### Gerar Relatórios

1. **Acesso:** Na interface gráfica, acesse o painel de geração de relatórios.
2. **Seleção de Aluno:** Selecione um aluno na lista disponível no JComboBox.
3. **Gerar Boletim:**
   - Clique no botão "Gerar Boletim" para gerar o boletim do aluno.
   - O arquivo será salvo no caminho: `F:\Samuel SENAI\Projeto-JAVA\boletim_aluno<ID_DO_ALUNO>.txt`.
4. **Gerar Relatório de Frequência:**
   - Clique no botão "Gerar Relatório de Frequência" para gerar o relatório de frequência do aluno.
   - O arquivo será salvo no caminho: `F:\Samuel SENAI\PROJETO-JAVA\frequencia_aluno<ID_DO_ALUNO>.txt`.

### Criar Aluno

1. **Acesso:** Na interface gráfica, acesse o painel de criação de aluno.
2. **Preenchimento:** Insira os dados do aluno nos campos correspondentes e selecione a turma desejada.
3. **Criação:** Clique no botão "Criar Aluno" para salvar os dados no banco de dados.
4. **Confirmação:** Uma mensagem de sucesso será exibida caso o aluno seja criado corretamente.

### Listar Alunos

1. **Acesso:** Na interface gráfica, acesse o painel de listagem de alunos.
2. **Visualização:** Todos os alunos cadastrados serão exibidos em uma tabela.
3. **Atualização:** Selecione um aluno e clique no botão "Atualizar" para continuar a edição dos dados do aluno selecionado. As informações poderão ser alteradas nos campos correspondentes, e a confirmação da atualização será feita após clicar no botão "Atualizar".
4. **Exclusão:** Para remover um aluno, selecione o aluno desejado e clique no botão "Excluir". Uma mensagem de confirmação será exibida antes da exclusão.

## Criar Professor
1. **Acesso:** Na interface gráfica, acesse o painel de criação de professor.
2. **Preenchimento:** Insira os dados do professor nos campos correspondentes.
3. Criação: Clique no botão "Criar Professor" para salvar os dados no banco de dados.
4. **Confirmação:** Uma mensagem de sucesso será exibida caso o professor seja criado corretamente.

## Listar Professores
1. **Acesso:** Na interface gráfica, acesse o painel de listagem de professores.
2. **Visualização:** Todos os professores cadastrados serão exibidos em uma tabela.
3. **Atualização:** Selecione um professor e clique no botão "Atualizar" para continuar a edição dos dados do professor selecionado. As informações poderão ser alteradas nos campos correspondentes, e a confirmação da atualização será feita após clicar no botão "Salvar".
4. **Exclusão:** Para remover um professor, selecione o professor desejado e clique no botão "Excluir". Uma mensagem de confirmação será exibida antes da exclusão.

## Mensagens de Erro e Validações

Durante o uso do sistema, diversas validações e mensagens de erro podem aparecer para garantir a integridade dos dados:

- **Campo Vazio:** Se algum campo obrigatório for deixado em branco, uma mensagem informando que "Todos os campos devem ser preenchidos" será exibida.
- **Dados Inválidos:** Para campos como CPF ou Data de Nascimento, se os dados inseridos não estiverem no formato correto, uma mensagem indicativa aparecerá, como "CPF inválido" ou "Data de Nascimento inválida".
- **Erro de Conexão:** Caso haja um problema de conexão com o banco de dados, uma mensagem de erro geral será exibida, informando que a operação não pode ser concluída no momento.
- **Sucesso nas Operações:** Mensagens de confirmação serão exibidas após operações bem-sucedidas, como "Disciplina criada com sucesso" ou "Aluno cadastrado com sucesso".

## Conclusão

Este manual fornece uma visão geral das funcionalidades e da interface do sistema de gerenciamento escolar. O sistema visa facilitar a administração de informações acadêmicas, tornando o processo de gerenciamento mais eficiente e organizado. Para qualquer dúvida ou assistência, consulte a documentação técnica ou entre em contato com o suporte técnico.