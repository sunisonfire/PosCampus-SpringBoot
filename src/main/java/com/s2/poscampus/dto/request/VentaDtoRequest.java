package com.s2.poscampus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Datos necesarios para registrar o actualizar una venta")
public record VentaDtoRequest(

        @Schema(description = "Número de documento del cliente", example = "1098765432")
        @NotNull(message = "El documento del cliente es obligatorio")
        @Positive(message = "El documento del cliente debe ser un número positivo")
        Long documentoCliente,

        @Schema(description = "Nombre completo del cliente", example = "Ana María Pérez",
                minLength = 2, maxLength = 50)
        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombreCompleto,

        @Schema(description = "Valor total de la venta", example = "160000")
        @NotNull(message = "El total es obligatorio")
        @Positive(message = "El total debe ser positivo")
        BigDecimal total
) {
}