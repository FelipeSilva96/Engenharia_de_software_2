package com.feedbackedu.service;

import com.feedbackedu.domain.Aluno;
import com.feedbackedu.domain.Feedback;
import com.feedbackedu.domain.Turma;
import com.feedbackedu.dto.FeedbackCreateDTO;
import com.feedbackedu.repository.AlunoRepository;
import com.feedbackedu.repository.FeedbackRepository;
import com.feedbackedu.repository.InscricaoRepository;
import com.feedbackedu.repository.TurmaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.feedbackedu.domain.Professor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FeedbackServiceTest {

    @Autowired
    private FeedbackService feedbackService;

    @MockBean
    private FeedbackRepository feedbackRepository;
    @MockBean
    private TurmaRepository turmaRepository;
    @MockBean
    private AlunoRepository alunoRepository;
    @MockBean
    private InscricaoRepository inscricaoRepository;

    private Aluno alunoTeste;
    private Turma turmaTeste;
    private FeedbackCreateDTO dtoValido;

    @BeforeEach
    void setUp() {

        alunoTeste = new Aluno("Aluno Teste", "aluno@teste.com", "123", "ALU123");
        alunoTeste.setIdUsuario(1L);

        turmaTeste = new Turma("Eng Software", "2025/2", new Professor());
        turmaTeste.setIdTurma(1L);

        dtoValido = new FeedbackCreateDTO(5, "Este é um comentário de feedback válido e longo.");

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(alunoTeste));

        when(turmaRepository.findById(1L)).thenReturn(Optional.of(turmaTeste));
    }

    @Test
    void submeterFeedback_ComDadosValidos_DeveSalvarFeedback() {

        when(inscricaoRepository.existsByAlunoAndTurma(alunoTeste, turmaTeste)).thenReturn(true);

        when(feedbackRepository.save(any(Feedback.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Feedback feedbackSalvo = feedbackService.submeterFeedback(dtoValido, turmaTeste.getIdTurma(), alunoTeste.getIdUsuario());

        assertNotNull(feedbackSalvo);
        assertEquals(dtoValido.comentario(), feedbackSalvo.getComentario());
        assertEquals(5, feedbackSalvo.getClassificacao());
        assertEquals(turmaTeste, feedbackSalvo.getTurma());
    }

    @Test
    void submeterFeedback_ComComentarioCurto_DeveLancarExcecao() {

        FeedbackCreateDTO dtoInvalido = new FeedbackCreateDTO(3, "Curto");

        when(inscricaoRepository.existsByAlunoAndTurma(alunoTeste, turmaTeste)).thenReturn(true);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            feedbackService.submeterFeedback(dtoInvalido, turmaTeste.getIdTurma(), alunoTeste.getIdUsuario());
        });

        assertEquals("O comentário deve ter no mínimo 15 caracteres.", excecao.getMessage());
    }

    @Test
    void submeterFeedback_AlunoNaoInscrito_DeveLancarExcecao() {

        when(inscricaoRepository.existsByAlunoAndTurma(alunoTeste, turmaTeste)).thenReturn(false);

        IllegalStateException excecao = assertThrows(IllegalStateException.class, () -> {
            feedbackService.submeterFeedback(dtoValido, turmaTeste.getIdTurma(), alunoTeste.getIdUsuario());
        });

        assertEquals("Aluno não está inscrito nesta turma.", excecao.getMessage());
    }
}
