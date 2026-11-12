package com.s2.poscampus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Resumen de un producto para mostrar dentro de un detalle de venta (sin stock ni precio de compra)")
public record ProductoDtoResponseFactura(

        @Schema(description = "Identificador único del producto", example = "1")
        Long id,

        @Schema(description = "Nombre del producto", example = "Teclado mecánico")
        String nombre,

        @Schema(description = "Marca del producto", example = "Logitech")
        String marca,

        @Schema(description = "Precio de venta al público", example = "80000")
        BigDecimal precioVenta
) {
}