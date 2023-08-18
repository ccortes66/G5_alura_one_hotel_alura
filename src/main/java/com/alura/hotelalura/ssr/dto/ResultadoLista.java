package com.alura.hotelalura.ssr.dto;

import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ReservaInfo;

import java.util.List;

public record ResultadoLista(List<ReservaInfo> infoList,
                             Usuario usuario) {
}
