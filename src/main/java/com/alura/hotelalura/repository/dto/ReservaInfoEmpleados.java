package com.alura.hotelalura.repository.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaInfoEmpleados(String reserva,
                                   LocalDate checkIn,
                                   LocalDate checkOut,
                                   String categoria,
                                   Integer habitacion,
                                   String metodoPago,
                                   Integer numero)
{
}
