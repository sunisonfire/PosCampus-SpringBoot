package com.s2.poscampus.dto.response;

import java.math.BigDecimal;

public record ProductoDtoResponseFactura(
        Long id,
        String nombre,
        String marca,
        BigDecimal precioVenta
) {
}
