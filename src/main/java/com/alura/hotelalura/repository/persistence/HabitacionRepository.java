package com.alura.hotelalura.repository.persistence;

import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.repository.dto.MostarHabitacionReserva;

import java.util.List;
import java.util.UUID;

public interface HabitacionRepository extends CompuestoRepository<Habitacion,Integer>
{

    List<Habitacion> listarHabitacionDisponible(String tipo);
    Byte guardar(Habitacion habitacion);

    Habitacion asignarHabitacion(String tipo);

    void guardarHabitacionPorReseva(Habitacion habitacion);

}
