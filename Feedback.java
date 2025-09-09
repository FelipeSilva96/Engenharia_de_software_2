package com.feedbackedu.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeedback;

    @Column(nullable = false)
    private int classificacao;

    @Column(nullable = false, length = 1000)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime dataEnvio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

    public Feedback() {
    }

    public Feedback(int classificacao, String comentario, Turma turma) {
        this.classificacao = classificacao;
        this.comentario = comentario;
        this.turma = turma;
        this.dataEnvio = LocalDateTime.now();
    }

    public Long getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(Long idFeedback) {
        this.idFeedback = idFeedback;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
