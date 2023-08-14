package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.repository.dto.ReservaInfo;

import java.util.List;

public record ListarBusqueda(List<ReservaInfo> lista,
                            ReservaInfo info) { }
