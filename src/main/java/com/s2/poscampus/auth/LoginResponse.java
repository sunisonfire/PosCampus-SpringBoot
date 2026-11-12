package com.s2.poscampus.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta del login con el token de acceso")
public record LoginResponse(String token) {
}
