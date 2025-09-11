package com.feedbackedu.dto;
public record UsuarioCreateDTO(
        String nome,
        String email,
        String senha,
        String matricula,
        String tipoUsuario
        ) {
}
