package com.alura.hotelalura.repository.dto;

import java.math.BigDecimal;

public record ReservaInfoSsr(String reserva,
                            BigDecimal valor,
                            Integer habitacion) {
}
