package com.s2.poscampus.dto.response;

import java.math.BigDecimal;

public record ProductoDtoResponse(
        Long id, String nombre, String marca, Integer stock, BigDecimal precioCompra, BigDecimal precioVenta
) {
}
