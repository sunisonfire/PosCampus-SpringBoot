package com.s2.poscampus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información completa de un producto del catálogo")
public record ProductoDtoResponse(

        @Schema(description = "Identificador único del producto", example = "1")
        Long id,

        @Schema(description = "Nombre del producto", example = "Teclado mecánico")
        String nombre,

        @Schema(description = "Marca del producto", example = "Logitech")
        String marca,

        @Schema(description = "Unidades disponibles en inventario", example = "25")
        Integer stock,

        @Schema(description = "Precio al que se compra el producto al proveedor", example = "50000")
        BigDecimal precioCompra,

        @Schema(description = "Precio al que se vende el producto al público", example = "80000")
        BigDecimal precioVenta
) {
}