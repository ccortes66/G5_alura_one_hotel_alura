package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.HabitacionTipo;

import java.util.List;

public record RtsReservacionEmpresa(List<HabitacionTipo> list,
                                    Usuario usuario,
                                    Boolean[] confirm) {
}
