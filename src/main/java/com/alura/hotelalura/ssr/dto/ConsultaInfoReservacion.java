package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.repository.dto.RegistrarReserva;
import com.alura.hotelalura.repository.dto.ReservaInfoSsr;
import com.alura.hotelalura.ssr.error.ErrorResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ConsultaInfoReservacion(List<HabitacionTipo> habitacionTipo,
                                      ErrorResponse response,
                                      RegistrarReserva reserva,
                                      Usuario usuario) {}
