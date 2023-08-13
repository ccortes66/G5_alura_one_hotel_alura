package com.alura.hotelalura.repository.dto;

import com.alura.hotelalura.model.Usuario;

public record RegistrarUsuario(Usuario usuario,
                                String username,
                                String password) { }
