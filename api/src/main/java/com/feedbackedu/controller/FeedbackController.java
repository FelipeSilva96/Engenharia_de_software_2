// Ficheiro: src/main/java/com/feedbackedu/controller/FeedbackController.java
package com.feedbackedu.controller;

import com.feedbackedu.domain.Feedback;
import com.feedbackedu.dto.FeedbackCreateDTO;
import com.feedbackedu.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas/{idTurma}/feedbacks") 
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> submeterFeedback(@PathVariable Long idTurma, @RequestBody FeedbackCreateDTO dto, @RequestParam Long idAluno) {
        Feedback novoFeedback = feedbackService.submeterFeedback(dto, idTurma, idAluno);
        return new ResponseEntity<>(novoFeedback, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> visualizarFeedbacks(@PathVariable Long idTurma, @RequestParam Long idProfessor) {
        List<Feedback> feedbacks = feedbackService.visualizarFeedbacks(idTurma, idProfessor);
        return ResponseEntity.ok(feedbacks);
    }
}
