package com.feedbackedu.service;

import com.feedbackedu.domain.Aluno;
import com.feedbackedu.domain.Inscricao;
import com.feedbackedu.domain.Professor;
import com.feedbackedu.domain.Turma;
import com.feedbackedu.dto.InscricaoDTO;
import com.feedbackedu.dto.TurmaCreateDTO;
import com.feedbackedu.repository.AlunoRepository; 
import com.feedbackedu.repository.InscricaoRepository;
import com.feedbackedu.repository.ProfessorRepository; 
import com.feedbackedu.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private InscricaoRepository inscricaoRepository;

    
    public Turma criarNovaTurma(TurmaCreateDTO dto, Long idProfessor) {
        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        Turma novaTurma = new Turma(dto.nomeDisciplina(), dto.periodoLetivo(), professor);
        return turmaRepository.save(novaTurma);
    }

    public Inscricao inscreverAluno(InscricaoDTO dto, Long idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        
        Turma turma = turmaRepository.findByCodigoInscricao(dto.codigoInscricao())
                .orElseThrow(() -> new IllegalArgumentException("Código da turma inválido."));

        
        if (inscricaoRepository.existsByAlunoAndTurma(aluno, turma)) {
            throw new IllegalArgumentException("Aluno já inscrito nesta turma.");
        }

        Inscricao novaInscricao = new Inscricao(aluno, turma);
        return inscricaoRepository.save(novaInscricao);
    }
}
