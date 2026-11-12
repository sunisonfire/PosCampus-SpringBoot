package com.s2.poscampus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Detalle de una venta, con la venta y el producto completos")
public record DetalleVentaDtoResponse(

        @Schema(description = "Identificador único del detalle", example = "1")
        Long id,

        @Schema(description = "Venta a la que pertenece el detalle")
        VentaDtoResponse venta,

        @Schema(description = "Producto vendido en este detalle")
        ProductoDtoResponseFactura producto,

        @Schema(description = "Unidades vendidas del producto", example = "2")
        Integer cantidad,

        @Schema(description = "Subtotal del detalle: cantidad × precio de venta", example = "160000")
        BigDecimal subtotal
) {
}