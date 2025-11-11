# Relatório de Implementação de Testes Automatizados

Este documento descreve os testes automatizados implementados para o projeto `feedback.edu`, conforme solicitado pela atividade acadêmica de "Implementação de testes automatizados".

**Link do Repositório:** https://github.com/FelipeSilva96/Engenharia_de_software_2

---

## 1. Ferramentas Utilizadas

* **Java 21** e **Spring Boot**
* **JUnit 5:** Framework de testes.
* **Spring Boot Test:** Para integração dos testes com o contexto da aplicação (`@SpringBootTest`, `@DataJpaTest`).
* **Mockito:** Para simulação (mock) de dependências em testes de unidade.
* **H2 Database:** Banco de dados em memória para testes da camada de persistência.

---

## 2. Escopo dos Testes

Os testes foram divididos em duas categorias principais, cobrindo o escopo mínimo solicitado pela atividade (Camada de Domínio e Camada de Persistência).

Ao todo, foram implementados **5 testes** customizados (além do teste `contextLoads()` padrão), distribuídos da seguinte forma:

### 2.1. Camada de Domínio (Testes de Unidade)

**Arquivo Testado:** `src/test/java/com/feedbackedu/service/FeedbackServiceTest.java`

O foco foi testar a lógica de negócio do `FeedbackService`, isolando-a com o uso de `@MockBean` para simular os repositórios.

* `submeterFeedback_ComDadosValidos_DeveSalvarFeedback`: Garante que um feedback com dados corretos (aluno inscrito, comentário longo) é salvo (Cenário de Sucesso RFF003).
* `submeterFeedback_ComComentarioCurto_DeveLancarExcecao`: Garante que a regra de negócio (mínimo de 15 caracteres) é aplicada, lançando uma `IllegalArgumentException` (Cenário de Falha RFF003).
* `submeterFeedback_AlunoNaoInscrito_DeveLancarExcecao`: Garante que a regra de negócio (aluno deve estar inscrito na turma) é aplicada, lançando uma `IllegalStateException`.

### 2.2. Camada de Persistência (Testes de Integração)

**Arquivo Testado:** `src/test/java/com/feedbackedu/repository/UsuarioRepositoryTest.java`

O foco foi testar a consulta customizada ao banco de dados usando `@DataJpaTest`, que configura automaticamente um banco de dados H2 em memória.

* `findByMatricula_QuandoUsuarioExiste_DeveRetornarUsuario`: Valida se a query `findByMatricula` consegue encontrar um usuário previamente salvo no banco de teste (Cenário RFF001).
* `findByMatricula_QuandoUsuarioNaoExiste_DeveRetornarVazio`: Valida o comportamento da query quando o usuário não existe no banco de teste.

---

## 3. Comprovação da Execução

Todos os 6 testes (5 da lógica e 1 de contexto) foram executados com sucesso através do comando `.\mvnw.cmd test`.

```
[INFO] Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```