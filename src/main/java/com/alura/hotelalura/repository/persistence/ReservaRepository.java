package com.alura.hotelalura.repository.persistence;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;

import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends CompuestoRepository<Reserva,String>
{
    List<ReservaInfo> listarReservaPorCliente(String dni);

    void generar(Reserva reserva);

    ReservaInfo buscar(String dni,String reserva);

    List<ReservaInfoEmpleados> listaReservaActual(Integer skip,Integer limit);
}
