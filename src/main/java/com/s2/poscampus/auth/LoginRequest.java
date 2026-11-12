package com.s2.poscampus.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciales para iniciar sesión")
public record LoginRequest(String username, String password) {

}
// modelo, dto: request y response, mapper, repository, service:imp y normal, controller(endpoints), 0