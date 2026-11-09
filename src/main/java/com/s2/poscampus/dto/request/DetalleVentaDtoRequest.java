package com.s2.poscampus.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DetalleVentaDtoRequest(
        //recibe solo los ids, no los objetos completos
        @NotNull(message = "Error, debe poner algo ")
        @Positive(message = "Error, no es posible una venta negativa")
        Long ventaId,
        @NotNull(message = "Error, no puede ser nulo")
        @Positive(message = "Error, no hay productos negativos")
        Long productoId,
        @NotNull(message = "Error, debe poner algo hpta")
        @Positive(message = "Error, desde cuando hay una cantidad en negativo pedazo de mongolo, arregle esa mrd")
        Integer cantidad,
        @NotNull(message = "Error, debe poner algo hpta")
        @Positive(message = "Error, desde cuando hay un subtotal en negativo pedazo de mongolo, arregle esa mrd")
        BigDecimal subtotal
) {
}

