package com.s2.poscampus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VentaDtoRequest(
        @Schema(description = "HOLAAAAA", example = "234567890")
        @NotNull(message = "Error, debe poner algo hpta")
        @Positive(message = "Error, desde cuando hay una identificacion en negativo pedazo de mongolo, arregle esa mrd")
        Long documentoCliente,
        @NotBlank(message = "Error, ponga algo idiota")
        @Size(min = 2, max = 150, message = "Error, el rango es de 2 a 150")
        String nombreCompleto,
        @NotNull(message = "Error, debe poner algo hpta")
        @Positive(message = "Error, el total debe ser positivo imbecil")
        BigDecimal total
) {
}
