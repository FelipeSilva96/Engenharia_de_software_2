# Projeto feedback.edu - API Back-end

Este repositório contém o código-fonte do back-end para a plataforma **feedback.edu**, um sistema projetado para facilitar a comunicação e o feedback anónimo entre alunos e professores.

A aplicação foi desenvolvida em **Java** com o framework **Spring Boot**, seguindo uma arquitetura em camadas bem definida para garantir manutenibilidade, testabilidade e escalabilidade.

## Estrutura do Projeto

O projeto está organizado em pacotes que refletem a sua arquitetura em camadas:

- **`src/main/java/com/feedbackedu`**
  - **`/controller`**: Contém os `Controllers` da API REST. Responsáveis por receber os pedidos HTTP, interagir com a camada de serviço e retornar as respostas.
  - **`/domain`**: O coração do sistema. Contém as entidades de negócio (`@Entity`) que modelam os conceitos principais da aplicação.
  - **`/dto`**: Contém os `Data Transfer Objects` (DTOs), objetos simples usados para transportar dados entre o cliente e a API de forma segura.
  - **`/repository`**: Contém as interfaces de `Repository` (Spring Data JPA), responsáveis por abstrair a comunicação com o banco de dados.
  - **`/service`**: Contém as classes de `Service`, onde reside toda a lógica de negócio, as regras e as validações do sistema.

- **`src/main/resources`**
  - **`application.properties`**: Ficheiro principal de configuração do Spring Boot, onde se define a conexão com o banco de dados e outras propriedades da aplicação.

---

## Delimitação das Camadas para Entrega

Para fins de avaliação e entrega das tarefas de forma separada, os artefactos de código correspondentes a cada camada estão organizados da seguinte forma:

### Artefactos da Camada de Domínio

A Camada de Domínio é responsável por representar os conceitos do negócio e conter as regras e lógicas centrais do sistema. Os ficheiros que compõem esta camada são:

- **Entidades de Domínio (As "peças" do sistema):**
  - `src/main/java/com/feedbackedu/domain/Usuario.java`
  - `src/main/java/com/feedbackedu/domain/Professor.java`
  - `src/main/java/com/feedbackedu/domain/Aluno.java`
  - `src/main/java/com/feedbackedu/domain/Turma.java`
  - `src/main/java/com/feedbackedu/domain/Inscricao.java`
  - `src/main/java/com/feedbackedu/domain/Feedback.java`

- **Serviços de Domínio (O "cérebro" com as regras de negócio):**
  - `src/main/java/com/feedbackedu/service/UsuarioService.java`
  - `src/main/java/com/feedbackedu/service/TurmaService.java`
  - `src/main/java/com/feedbackedu/service/FeedbackService.java`

### Artefactos da Camada de Persistência

A Camada de Persistência é responsável por abstrair e gerir a comunicação com o banco de dados, escondendo os detalhes de armazenamento do resto da aplicação.

- **Repositórios / DAOs (A "ponte" para os dados):**
  - `src/main/java/com/feedbackedu/repository/UsuarioRepository.java`
  - `src/main/java/com/feedbackedu/repository/ProfessorRepository.java`
  - `src/main/java/com/feedbackedu/repository/AlunoRepository.java`
  - `src/main/java/com/feedbackedu/repository/TurmaRepository.java`
  - `src/main/java/com/feedbackedu/repository/InscricaoRepository.java`
  - `src/main/java/com/feedbackedu/repository/FeedbackRepository.java`

- **Configuração da Conexão:**
  - `src/main/resources/application.properties` (especificamente as propriedades `spring.datasource.*` e `spring.jpa.*`)

---

## Como Executar o Projeto

### Pré-requisitos
1.  **Java JDK 17** ou superior.
2.  **Maven** (a aplicação utiliza o Maven Wrapper, pelo que não é necessária uma instalação manual).
3.  Um servidor de banco de dados **MySQL** a ser executado (ex: via XAMPP).

### Passos
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/FelipeSilva96/Engenharia_de_software_2/tree/main
    cd api
    ```
2.  **Configure a Base de Dados:**
    - Crie uma base de dados vazia no seu MySQL com o nome `feedbackedu_db`.
    - Abra o ficheiro `src/main/resources/application.properties` e atualize as propriedades `spring.datasource.username` e `spring.datasource.password` com as suas credenciais do MySQL.

3.  **Execute a Aplicação:**
    - Abra um terminal na pasta raiz do projeto (a pasta que contém o `pom.xml`).
    - Execute o seguinte comando:
      ```bash
      # Para Windows
      .\mvnw.cmd spring-boot:run
      
      # Para Linux/Mac
      ./mvnw spring-boot:run
      ```
    - A aplicação será iniciada e estará disponível em `http://localhost:8080`. O Hibernate irá criar automaticamente todas as tabelas na base de dados na primeira execução.
```eof