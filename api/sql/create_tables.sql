CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `matricula` varchar(20) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_email` (`email`),
  UNIQUE KEY `UK_matricula` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `aluno` (
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `fk_aluno_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `professor` (
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `fk_professor_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `turma` (
  `id_turma` bigint NOT NULL AUTO_INCREMENT,
  `codigo_inscricao` varchar(255) NOT NULL,
  `nome_disciplina` varchar(255) NOT NULL,
  `periodo_letivo` varchar(10) NOT NULL,
  `id_professor` bigint NOT NULL,
  PRIMARY KEY (`id_turma`),
  UNIQUE KEY `UK_codigo_inscricao` (`codigo_inscricao`),
  KEY `fk_turma_professor` (`id_professor`),
  CONSTRAINT `fk_turma_professor` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `inscricao` (
  `id_inscricao` bigint NOT NULL AUTO_INCREMENT,
  `data_inscricao` date NOT NULL,
  `id_aluno` bigint NOT NULL,
  `id_turma` bigint NOT NULL,
  PRIMARY KEY (`id_inscricao`),
  KEY `fk_inscricao_aluno` (`id_aluno`),
  KEY `fk_inscricao_turma` (`id_turma`),
  CONSTRAINT `fk_inscricao_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_usuario`),
  CONSTRAINT `fk_inscricao_turma` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `feedback` (
  `id_feedback` bigint NOT NULL AUTO_INCREMENT,
  `classificacao` int NOT NULL,
  `comentario` varchar(1000) NOT NULL,
  `data_envio` datetime(6) NOT NULL,
  `id_turma` bigint NOT NULL,
  PRIMARY KEY (`id_feedback`),
  KEY `fk_feedback_turma` (`id_turma`),
  CONSTRAINT `fk_feedback_turma` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
