package com.alura.hotelalura.repository.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReservaInfo(String reserva,
                          LocalDate checkIn,
                          LocalDate checkOut,
                          BigDecimal precio,
                          String categoria,
                          Integer habitacion) {}
