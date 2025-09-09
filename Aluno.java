package com.feedbackedu.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Aluno extends Usuario {

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inscricao> inscricoes = new HashSet<>();

    public Aluno() {
        super();
    }

    public Aluno(String nome, String email, String senha, String matricula) {
        super(nome, email, senha, matricula);
    }

    public Set<Inscricao> getInscricoes() {
        return inscricoes;  
    }

    public void setInscricoes(Set<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }
}
