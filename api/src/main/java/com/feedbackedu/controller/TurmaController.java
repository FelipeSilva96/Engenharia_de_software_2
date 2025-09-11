package com.feedbackedu.controller;

import com.feedbackedu.domain.Inscricao;
import com.feedbackedu.domain.Turma;
import com.feedbackedu.dto.InscricaoDTO;
import com.feedbackedu.dto.TurmaCreateDTO;
import com.feedbackedu.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody TurmaCreateDTO dto, @RequestParam Long idProfessor) {
        Turma novaTurma = turmaService.criarNovaTurma(dto, idProfessor);
        return new ResponseEntity<>(novaTurma, HttpStatus.CREATED);
    }

    @PostMapping("/inscricoes")
    public ResponseEntity<Inscricao> inscreverAluno(@RequestBody InscricaoDTO dto, @RequestParam Long idAluno) {
        Inscricao novaInscricao = turmaService.inscreverAluno(dto, idAluno);
        return new ResponseEntity<>(novaInscricao, HttpStatus.CREATED);
    }
}
