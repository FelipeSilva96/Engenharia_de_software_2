# Projeto: feedback.edu - Camada de Domínio

Este diretório contém a implementação das classes de entidade da camada de domínio do sistema feedback.edu.

## Descrição das Classes

- **Usuario.java**: Classe base abstrata que define os atributos comuns a todos os usuários (Professores e Alunos), como nome, email, senha e matrícula.
- **Professor.java**: Representa o ator Professor. Herda de `Usuario` e se relaciona com as `Turmas` que leciona.
- **Aluno.java**: Representa o ator Aluno. Herda de `Usuario` e se relaciona com as `Turmas` através da entidade `Inscricao`.
- **Turma.java**: Modela uma disciplina ou turma, contendo seu nome, código e os relacionamentos com o professor, os alunos inscritos e os feedbacks recebidos.
- **Inscricao.java**: Entidade associativa que conecta um `Aluno` a uma `Turma`, formalizando a matrícula.
- **Feedback.java**: Armazena o conteúdo de um feedback (classificação e comentário), relacionando-se apenas com a `Turma` para garantir o anonimato do aluno.
