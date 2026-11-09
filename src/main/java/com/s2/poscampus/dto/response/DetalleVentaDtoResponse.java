package com.s2.poscampus.dto.response;

import java.math.BigDecimal;

public record DetalleVentaDtoResponse(

        //devuelve el detalle con la venta y producto completos
        Long id,
        VentaDtoResponse venta,
        ProductoDtoResponseFactura producto,
        Integer cantidad,
        BigDecimal subtotal
) {
}
