package com.s2.poscampus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información de una venta registrada")
public record VentaDtoResponse(

        @Schema(description = "Identificador único de la venta", example = "1")
        Long id,

        @Schema(description = "Número de documento del cliente", example = "1098765432")
        Long documentoCliente,

        @Schema(description = "Nombre completo del cliente", example = "Ana María Pérez")
        String nombreCompleto,

        @Schema(description = "Valor total de la venta", example = "160000")
        BigDecimal total
) {
}