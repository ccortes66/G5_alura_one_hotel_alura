package com.alura.hotelalura.repository.dto;

import java.time.LocalDate;
import java.util.UUID;


public class MostarHabitacionReserva
{
    UUID reserva;
    LocalDate checkIn;
    LocalDate checkOut;
    Integer numero;

    public MostarHabitacionReserva(UUID reserva, LocalDate checkIn, LocalDate checkOut, Integer numero) {
        this.reserva = reserva;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numero = numero;
    }
}
