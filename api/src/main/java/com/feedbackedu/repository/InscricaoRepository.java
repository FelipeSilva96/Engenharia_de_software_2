package com.feedbackedu.repository;

import com.feedbackedu.domain.Inscricao;
import com.feedbackedu.domain.Aluno;
import com.feedbackedu.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    boolean existsByAlunoAndTurma(Aluno aluno, Turma turma);
}