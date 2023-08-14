package com.alura.hotelalura.repository.persistence;

import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.repository.dto.RegistrarReserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface HabitacionTipoRepository extends UnitarioRepository<HabitacionTipo>
{
    BigDecimal consultarPrecioHabitacion(RegistrarReserva habitacion);

    BigDecimal consultarPrecioHabitacion(LocalDate checkInt, LocalDate checkOut,String tipoHabitacion);
}
