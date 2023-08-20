package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;

public record ListarBusquedaEmpleado(ReservaInfoEmpleados infoEmpleados,
                                     Usuario usuario,
                                     Boolean confirm) { }
