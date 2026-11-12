package com.s2.poscampus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Datos para registrar o actualizar un detalle de venta. "
        + "Recibe solo los IDs de la venta y del producto, no los objetos completos")
public record DetalleVentaDtoRequest(

        @Schema(description = "ID de la venta a la que pertenece el detalle (debe existir)", example = "1")
        @NotNull(message = "El ID de la venta es obligatorio")
        @Positive(message = "El ID de la venta debe ser positivo")
        Long ventaId,

        @Schema(description = "ID del producto vendido (debe existir)", example = "1")
        @NotNull(message = "El ID del producto es obligatorio")
        @Positive(message = "El ID del producto debe ser positivo")
        Long productoId,

        @Schema(description = "Unidades vendidas del producto (máximo 250 por detalle)",
                example = "2", maximum = "250")
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser positiva")
        Integer cantidad,

        @Schema(description = "Subtotal del detalle: cantidad × precio de venta del producto", example = "160000")
        @NotNull(message = "El subtotal es obligatorio")
        @Positive(message = "El subtotal debe ser positivo")
        BigDecimal subtotal
) {
}