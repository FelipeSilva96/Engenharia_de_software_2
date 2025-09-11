package com.feedbackedu.controller;

import com.feedbackedu.domain.Usuario;
import com.feedbackedu.dto.UsuarioCreateDTO;
import com.feedbackedu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioCreateDTO dto) {
        Usuario novoUsuario = usuarioService.criarNovoUsuario(dto);

        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
}
