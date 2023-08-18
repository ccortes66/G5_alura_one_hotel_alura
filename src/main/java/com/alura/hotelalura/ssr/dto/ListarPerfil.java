package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Usuario;

import java.util.TreeSet;

public record ListarPerfil(TreeSet<String> mysPaises,
                           Cliente cliente) {
}
