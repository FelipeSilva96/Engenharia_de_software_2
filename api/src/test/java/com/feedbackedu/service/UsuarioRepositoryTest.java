package com.feedbackedu.repository;

import com.feedbackedu.domain.Aluno;
import com.feedbackedu.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void findByMatricula_QuandoUsuarioExiste_DeveRetornarUsuario() {

        Aluno aluno = new Aluno("Aluno Teste", "aluno@teste.com", "senha123", "MATRICULA_TESTE");
        entityManager.persistAndFlush(aluno);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByMatricula("MATRICULA_TESTE");

        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("MATRICULA_TESTE", usuarioEncontrado.get().getMatricula());
    }

    @Test
    void findByMatricula_QuandoUsuarioNaoExiste_DeveRetornarVazio() {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByMatricula("MATRICULA_FANTASMA");

        assertFalse(usuarioEncontrado.isPresent());
    }
}
