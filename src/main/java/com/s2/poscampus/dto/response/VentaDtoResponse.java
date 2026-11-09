package com.s2.poscampus.dto.response;

import java.math.BigDecimal;

public record VentaDtoResponse(
        Long id,
        Long documentoCliente,
        String nombreCompleto,
        BigDecimal total
) {
}
