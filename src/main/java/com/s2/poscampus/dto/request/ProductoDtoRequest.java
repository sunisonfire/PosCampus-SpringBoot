package com.s2.poscampus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public record ProductoDtoRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 50)
        String nombre,
        @NotBlank(message = "La marca no puede estar vacia")
        @Size(min = 2, max = 50)
        String marca,
        @NotNull(message = "Error el stock no puede estar nulo")
        @Positive(message = "Error, el stock debe ser positivo")
        Integer stock,
        @Positive(message = "Error, debe ser positivo")
        @NotNull(message = "Error, no puede ser nulo")
        BigDecimal precioCompra,
        @Positive(message = "Error, debe ser positivo")
        @NotNull(message = "Error, no puede ser nulo")
        BigDecimal precioVenta
) {
}