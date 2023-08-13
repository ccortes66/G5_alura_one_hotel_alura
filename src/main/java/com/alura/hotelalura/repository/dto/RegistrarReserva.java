package com.alura.hotelalura.repository.dto;

import java.time.LocalDate;

public record RegistrarReserva(LocalDate checkIn,
                               LocalDate checkOut,
                               String tipo,
                               String metodoPago){ }
