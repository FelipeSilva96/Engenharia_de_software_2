package com.feedbackedu.service;

import com.feedbackedu.domain.Feedback;
import com.feedbackedu.domain.Turma;
import com.feedbackedu.dto.FeedbackCreateDTO;
import com.feedbackedu.repository.FeedbackRepository;
import com.feedbackedu.repository.InscricaoRepository;
import com.feedbackedu.repository.TurmaRepository;
import com.feedbackedu.repository.AlunoRepository;
import com.feedbackedu.domain.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Feedback submeterFeedback(FeedbackCreateDTO dto, Long idTurma, Long idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));
        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));

        if (!inscricaoRepository.existsByAlunoAndTurma(aluno, turma)) {
            throw new IllegalStateException("Aluno não está inscrito nesta turma.");
        }

        if (dto.comentario().length() < 15) {
            throw new IllegalArgumentException("O comentário deve ter no mínimo 15 caracteres.");
        }

        Feedback novoFeedback = new Feedback(dto.classificacao(), dto.comentario(), turma);

        return feedbackRepository.save(novoFeedback);
    }

    public List<Feedback> visualizarFeedbacks(Long idTurma, Long idProfessor) {
        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));

        if (!turma.getProfessor().getIdUsuario().equals(idProfessor)) {
            throw new IllegalStateException("Acesso negado. Você não é o professor desta turma.");
        }

        return turma.getFeedbacks();
    }
}
