
package com.feedbackedu.repository;

import com.feedbackedu.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    Optional<Turma> findByCodigoInscricao(String codigoInscricao);
}