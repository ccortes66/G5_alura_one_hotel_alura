package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.model.type.MetodoPago;
import com.alura.hotelalura.repository.dto.ReservaInfoSsr;
import com.alura.hotelalura.ssr.error.ErrorResponse;

import java.util.List;

public record ListarCatrgoriaMetodo(List<MetodoPago> metodoPago,
                                    List<HabitacionTipo> habitacionTipo,
                                    ErrorResponse response,
                                    ReservaInfoSsr info,
                                    Usuario usuario) {
}
