package com.feedbackedu.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turma> turmas = new ArrayList<>();

    public Professor() {
        super();
    }

    public Professor(String nome, String email, String senha, String matricula) {
        super(nome, email, senha, matricula);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
        turma.setProfessor(this);
    }
}
