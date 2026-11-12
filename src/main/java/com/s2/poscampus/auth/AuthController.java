package com.s2.poscampus.auth;

import com.s2.poscampus.config.JwtService;
import com.s2.poscampus.exception.BussinesRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@Tag(name = "Auth Controller",
        description = "Autenticación: permite iniciar sesión y obtener el token JWT para usar la API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    @Operation(summary = "Iniciar sesión",
            description = "Valida usuario y contraseña y devuelve un token JWT. "
                    + "Ese token se envía en el botón Authorize de Swagger (o en el header Authorization: Bearer <token>) "
                    + "para consumir los demás endpoints")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso, devuelve el token JWT",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        if (request.username().equals("admin") &&
                request.password().equals("1234")) {

            String token = jwtService.generateToken(request.username());
            return Map.of("token", token);
        }

        throw new BussinesRuleException("Credenciales inválidas");
    }
}