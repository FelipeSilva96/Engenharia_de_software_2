# Projeto: feedback.edu - Camada de Domínio

Este diretório contém a implementação das classes de entidade da camada de domínio do sistema feedback.edu.

## Descrição das Classes

- **Usuario.java**: Classe base abstrata que define os atributos comuns a todos os usuários (Professores e Alunos), como nome, email, senha e matrícula.
- **Professor.java**: Representa o ator Professor. Herda de `Usuario` e se relaciona com as `Turmas` que leciona.
- **Aluno.java**: Representa o ator Aluno. Herda de `Usuario` e se relaciona com as `Turmas` através da entidade `Inscricao`.
- **Turma.java**: Modela uma disciplina ou turma, contendo seu nome, código e os relacionamentos com o professor, os alunos inscritos e os feedbacks recebidos.
- **Inscricao.java**: Entidade associativa que conecta um `Aluno` a uma `Turma`, formalizando a matrícula.
- **Feedback.java**: Armazena o conteúdo de um feedback (classificação e comentário), relacionando-se apenas com a `Turma` para garantir o anonimato do aluno.

Projeto feedback.edu - API Back-end
Este repositório contém o código-fonte do back-end para a plataforma feedback.edu, um sistema projetado para facilitar a comunicação e o feedback anónimo entre alunos e professores.

A aplicação foi desenvolvida em Java com o framework Spring Boot, seguindo uma arquitetura em camadas bem definida para garantir manutenibilidade, testabilidade e escalabilidade.

Estrutura do Projeto
O projeto está organizado em pacotes que refletem a sua arquitetura em camadas:

src/main/java/com/feedbackedu

/controller: Contém os Controllers da API REST. Responsáveis por receber os pedidos HTTP, interagir com a camada de serviço e retornar as respostas.

/domain: O coração do sistema. Contém as entidades de negócio (@Entity) que modelam os conceitos principais da aplicação.

/dto: Contém os Data Transfer Objects (DTOs), objetos simples usados para transportar dados entre o cliente e a API de forma segura.

/repository: Contém as interfaces de Repository (Spring Data JPA), responsáveis por abstrair a comunicação com o banco de dados.

/service: Contém as classes de Service, onde reside toda a lógica de negócio, as regras e as validações do sistema.

src/main/resources

application.properties: Ficheiro principal de configuração do Spring Boot, onde se define a conexão com o banco de dados e outras propriedades da aplicação.

Descrição das Classes de Domínio (/domain)
As classes de domínio são a representação em código dos conceitos fundamentais do sistema:

Usuario.java: Classe base abstrata que define os atributos comuns a todos os utilizadores (Professores e Alunos), como nome, email, senha e matrícula.

Professor.java: Representa o ator Professor. Herda de Usuario e relaciona-se com as Turmas que leciona.

Aluno.java: Representa o ator Aluno. Herda de Usuario e relaciona-se com as Turmas através da entidade Inscricao.

Turma.java: Modela uma disciplina ou turma, contendo o seu nome, código de inscrição e os relacionamentos com o professor, os alunos inscritos e os feedbacks recebidos.

Inscricao.java: Entidade associativa que formaliza a relação muitos-para-muitos entre um Aluno e uma Turma.

Feedback.java: Armazena o conteúdo de um feedback (classificação e comentário). Relaciona-se apenas com a Turma para garantir o anonimato do aluno.

Como Executar o Projeto
Pré-requisitos
Java JDK 17 ou superior.

Maven (a aplicação utiliza o Maven Wrapper, pelo que não é necessária uma instalação manual).

Um servidor de banco de dados MySQL a ser executado (ex: via XAMPP).

Passos
Clone o repositório:

git clone https://github.com/FelipeSilva96/Engenharia_de_software_2
cd api

Configure a Base de Dados:

Crie uma base de dados vazia no seu MySQL com o nome feedbackedu_db.

Abra o ficheiro src/main/resources/application.properties e atualize as propriedades spring.datasource.username e spring.datasource.password com as suas credenciais do MySQL.

**Delimitação das Camadas para Entrega**  

Para fins de avaliação e entrega das tarefas de forma separada, os artefactos de código correspondentes a cada camada estão organizados da seguinte forma:  

**Artefactos da Camada de Domínio**  

A Camada de Domínio é responsável por representar os conceitos do negócio e conter as regras e lógicas centrais do sistema. Os ficheiros que compõem esta camada são:  


Entidades de Domínio (As "peças" do sistema):  


src/main/java/com/feedbackedu/domain/Usuario.java  

src/main/java/com/feedbackedu/domain/Professor.java  

src/main/java/com/feedbackedu/domain/Aluno.java  

src/main/java/com/feedbackedu/domain/Turma.java  

src/main/java/com/feedbackedu/domain/Inscricao.java

src/main/java/com/feedbackedu/domain/Feedback.java  

Serviços de Domínio (O "cérebro" com as regras de negócio):  

src/main/java/com/feedbackedu/service/UsuarioService.java  

src/main/java/com/feedbackedu/service/TurmaService.java  

src/main/java/com/feedbackedu/service/FeedbackService.java  

**Artefactos da Camada de Persistência**  

A Camada de Persistência é responsável por abstrair e gerir a comunicação com o banco de dados, escondendo os detalhes de armazenamento do resto da aplicação.  

Repositórios / DAOs (A "ponte" para os dados):  

src/main/java/com/feedbackedu/repository/UsuarioRepository.java  

src/main/java/com/feedbackedu/repository/ProfessorRepository.java  

src/main/java/com/feedbackedu/repository/AlunoRepository.java  

src/main/java/com/feedbackedu/repository/TurmaRepository.java  

src/main/java/com/feedbackedu/repository/InscricaoRepository.java  

src/main/java/com/feedbackedu/repository/FeedbackRepository.java  

Configuração da Conexão:  

src/main/resources/application.properties (especificamente as propriedades spring.datasource.* e spring.jpa.*)

Delimitação das Camadas para Entrega**
Para fins de avaliação e entrega das tarefas de forma separada, os artefactos de código correspondentes a cada camada estão organizados da seguinte forma:

Artefactos da Camada de Domínio
A Camada de Domínio é responsável por representar os conceitos do negócio e conter as regras e lógicas centrais do sistema. Os ficheiros que compõem esta camada são:

Entidades de Domínio (As "peças" do sistema):

src/main/java/com/feedbackedu/domain/Usuario.java

src/main/java/com/feedbackedu/domain/Professor.java

src/main/java/com/feedbackedu/domain/Aluno.java

src/main/java/com/feedbackedu/domain/Turma.java

src/main/java/com/feedbackedu/domain/Inscricao.java

src/main/java/com/feedbackedu/domain/Feedback.java

Serviços de Domínio (O "cérebro" com as regras de negócio):

src/main/java/com/feedbackedu/service/UsuarioService.java

src/main/java/com/feedbackedu/service/TurmaService.java

src/main/java/com/feedbackedu/service/FeedbackService.java

Artefactos da Camada de Persistência
A Camada de Persistência é responsável por abstrair e gerir a comunicação com o banco de dados, escondendo os detalhes de armazenamento do resto da aplicação.

Repositórios / DAOs (A "ponte" para os dados):

src/main/java/com/feedbackedu/repository/UsuarioRepository.java

src/main/java/com/feedbackedu/repository/ProfessorRepository.java

src/main/java/com/feedbackedu/repository/AlunoRepository.java

src/main/java/com/feedbackedu/repository/TurmaRepository.java

src/main/java/com/feedbackedu/repository/InscricaoRepository.java

src/main/java/com/feedbackedu/repository/FeedbackRepository.java

Configuração da Conexão:

src/main/resources/application.properties (especificamente as proprieDelimitação das Camadas para Entrega
Para fins de avaliação e entrega das tarefas de forma separada, os artefactos de código correspondentes a cada camada estão organizados da seguinte forma:

Artefactos da Camada de Domínio
A Camada de Domínio é responsável por representar os conceitos do negócio e conter as regras e lógicas centrais do sistema. Os ficheiros que compõem esta camada são:

Entidades de Domínio (As "peças" do sistema):

src/main/java/com/feedbackedu/domain/Usuario.java

src/main/java/com/feedbackedu/domain/Professor.java

src/main/java/com/feedbackedu/domain/Aluno.java

src/main/java/com/feedbackedu/domain/Turma.java

src/main/java/com/feedbackedu/domain/Inscricao.java

src/main/java/com/feedbackedu/domain/Feedback.java

Serviços de Domínio (O "cérebro" com as regras de negócio):

src/main/java/com/feedbackedu/service/UsuarioService.java

src/main/java/com/feedbackedu/service/TurmaService.java

src/main/java/com/feedbackedu/service/FeedbackService.java

Artefactos da Camada de Persistência
A Camada de Persistência é responsável por abstrair e gerir a comunicação com o banco de dados, escondendo os detalhes de armazenamento do resto da aplicação.

Repositórios / DAOs (A "ponte" para os dados):

src/main/java/com/feedbackedu/repository/UsuarioRepository.java

src/main/java/com/feedbackedu/repository/ProfessorRepository.java

src/main/java/com/feedbackedu/repository/AlunoRepository.java

src/main/java/com/feedbackedu/repository/TurmaRepository.java

src/main/java/com/feedbackedu/repository/InscricaoRepository.java

src/main/java/com/feedbackedu/repository/FeedbackRepository.java

Configuração da Conexão:

src/main/resources/application.properties (especificamente as propriedades spring.datasource.* e spring.jpa.*)dades spring.datasource.* e spring.jpa.*)
Execute a Aplicação:Delimitação das Camadas para Entrega
Para fins de avaliação e entrega das tarefas de forma separada, os artefactos de código correspondentes a cada camada estão organizados da seguinte forma:

Artefactos da Camada de Domínio
A Camada de Domínio é responsável por representar os conceitos do negócio e conter as regras e lógicas centrais do sistema. Os ficheiros que compõem esta camada são:

Entidades de Domínio (As "peças" do sistema):

src/main/java/com/feedbackedu/domain/Usuario.java

src/main/java/com/feedbackedu/domain/Professor.java

src/main/java/com/feedbackedu/domain/Aluno.java

src/main/java/com/feedbackedu/domain/Turma.java

src/main/java/com/feedbackedu/domain/Inscricao.java

src/main/java/com/feedbackedu/domain/Feedback.java

Serviços de Domínio (O "cérebro" com as regras de negócio):

src/main/java/com/feedbackedu/service/UsuarioService.java

src/main/java/com/feedbackedu/service/TurmaService.java

src/main/java/com/feedbackedu/service/FeedbackService.java

Artefactos da Camada de Persistência  

A Camada de Persistência é responsável por abstrair e gerir a comunicação com o banco de dados, escondendo os detalhes de armazenamento do resto da aplicação.

Repositórios / DAOs (A "ponte" para os dados):

src/main/java/com/feedbackedu/repository/UsuarioRepository.java

src/main/java/com/feedbackedu/repository/ProfessorRepository.java

src/main/java/com/feedbackedu/repository/AlunoRepository.java

src/main/java/com/feedbackedu/repository/TurmaRepository.java

src/main/java/com/feedbackedu/repository/InscricaoRepository.java

src/main/java/com/feedbackedu/repository/FeedbackRepository.java

Configuração da Conexão:

src/main/resources/application.properties (especificamente as propriedades spring.datasource.* e spring.jpa.*)Delimitação das Camadas para Entrega  

Para fins de avaliação e entrega das tarefas de forma separada, os artefactos de código correspondentes a cada camada estão organizados da seguinte forma:

Artefactos da Camada de Domínio  

A Camada de Domínio é responsável por representar os conceitos do negócio e conter as regras e lógicas centrais do sistema. Os ficheiros que compõem esta camada são:

Entidades de Domínio (As "peças" do sistema):

src/main/java/com/feedbackedu/domain/Usuario.java

src/main/java/com/feedbackedu/domain/Professor.java

src/main/java/com/feedbackedu/domain/Aluno.java

src/main/java/com/feedbackedu/domain/Turma.java

src/main/java/com/feedbackedu/domain/Inscricao.java

src/main/java/com/feedbackedu/domain/Feedback.java

Serviços de Domínio (O "cérebro" com as regras de negócio):

src/main/java/com/feedbackedu/service/UsuarioService.java

src/main/java/com/feedbackedu/service/TurmaService.java

src/main/java/com/feedbackedu/service/FeedbackService.java

Artefactos da Camada de Persistência  

A Camada de Persistência é responsável por abstrair e gerir a comunicação com o banco de dados, escondendo os detalhes de armazenamento do resto da aplicação.

Repositórios / DAOs (A "ponte" para os dados):

src/main/java/com/feedbackedu/repository/UsuarioRepository.java

src/main/java/com/feedbackedu/repository/ProfessorRepository.java

src/main/java/com/feedbackedu/repository/AlunoRepository.java

src/main/java/com/feedbackedu/repository/TurmaRepository.java

src/main/java/com/feedbackedu/repository/InscricaoRepository.java

src/main/java/com/feedbackedu/repository/FeedbackRepository.java

Configuração da Conexão:

src/main/resources/application.properties (especificamente as propriedades spring.datasource.* e spring.jpa.*)   

Abra um terminal na pasta raiz do projeto (a pasta que contém o pom.xml).  

Execute o seguinte comando:

# Para Windows
.\mvnw.cmd spring-boot:run

# Para Linux/Mac
./mvnw spring-boot:run

A aplicação será iniciada e estará disponível em http://localhost:8080. O Hibernate irá criar automaticamente todas as tabelas na base de dados na primeira execução.
