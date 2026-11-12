package com.s2.poscampus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Datos necesarios para registrar o actualizar un producto")
public record ProductoDtoRequest(

        @Schema(description = "Nombre del producto", example = "Teclado mecánico",
                minLength = 2, maxLength = 50)
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(description = "Marca del producto", example = "Logitech",
                minLength = 2, maxLength = 50)
        @NotBlank(message = "La marca no puede estar vacía")
        @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
        String marca,

        @Schema(description = "Unidades disponibles en inventario", example = "25")
        @NotNull(message = "El stock es obligatorio")
        @Positive(message = "El stock debe ser positivo")
        Integer stock,

        @Schema(description = "Precio al que se compra el producto al proveedor", example = "50000")
        @NotNull(message = "El precio de compra es obligatorio")
        @Positive(message = "El precio de compra debe ser positivo")
        BigDecimal precioCompra,

        @Schema(description = "Precio al que se vende el producto al público", example = "80000")
        @NotNull(message = "El precio de venta es obligatorio")
        @Positive(message = "El precio de venta debe ser positivo")
        BigDecimal precioVenta
) {
}