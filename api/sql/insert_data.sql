INSERT INTO usuario (nome, email, senha, matricula) VALUES ('Prof. Ana Costa', 'ana.costa@email.com', '{senha_criptografada}', 'PROF202501');

SET @prof_id = LAST_INSERT_ID();

INSERT INTO professor (id_usuario) VALUES (@prof_id);

INSERT INTO usuario (nome, email, senha, matricula) VALUES ('Bruno Silva', 'bruno.silva@email.com', '{senha_criptografada}', 'ALUNO202501');
SET @aluno1_id = LAST_INSERT_ID();
INSERT INTO aluno (id_usuario) VALUES (@aluno1_id);

INSERT INTO usuario (nome, email, senha, matricula) VALUES ('Carla Mendes', 'carla.mendes@email.com', '{senha_criptografada}', 'ALUNO202502');
SET @aluno2_id = LAST_INSERT_ID();
INSERT INTO aluno (id_usuario) VALUES (@aluno2_id);

INSERT INTO turma (nome_disciplina, periodo_letivo, codigo_inscricao, id_professor) VALUES ('Engenharia de Software II', '2025/2', 'ABC-123', @prof_id);
SET @turma_id = LAST_INSERT_ID();

INSERT INTO inscricao (data_inscricao, id_aluno, id_turma) VALUES (CURDATE(), @aluno1_id, @turma_id);
INSERT INTO inscricao (data_inscricao, id_aluno, id_turma) VALUES (CURDATE(), @aluno2_id, @turma_id);

INSERT INTO feedback (classificacao, comentario, data_envio, id_turma) VALUES (5, 'Excelente aula sobre arquitetura de software! Muito clara e com ótimos exemplos práticos.', NOW(), @turma_id);
