package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;

import java.util.List;

public record ResultadoListaEmpleado(List<ReservaInfoEmpleados> lista,
                                     Usuario usuario) {
}
