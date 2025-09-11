package com.feedbackedu.service;

import com.feedbackedu.domain.Aluno;
import com.feedbackedu.domain.Professor;
import com.feedbackedu.domain.Usuario;
import com.feedbackedu.dto.UsuarioCreateDTO;
import com.feedbackedu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarNovoUsuario(UsuarioCreateDTO dto) {

        if (usuarioRepository.findByMatricula(dto.matricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já cadastrada.");
        }

        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario novoUsuario;
        if ("ALUNO".equalsIgnoreCase(dto.tipoUsuario())) {
            novoUsuario = new Aluno(dto.nome(), dto.email(), dto.senha(), dto.matricula());
        } else if ("PROFESSOR".equalsIgnoreCase(dto.tipoUsuario())) {
            novoUsuario = new Professor(dto.nome(), dto.email(), dto.senha(), dto.matricula());
        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido. Use 'ALUNO' ou 'PROFESSOR'.");
        }

        return usuarioRepository.save(novoUsuario);
    }
}
