package com.s2.poscampus.controller;

import com.s2.poscampus.dto.request.VentaDtoRequest;
import com.s2.poscampus.dto.response.VentaDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.s2.poscampus.service.VentaService;

import java.util.List;

@Tag(name = "Venta Controller",
        description = "Gestiona las ventas del sistema: registro, consulta, actualización y eliminación")
@RestController
@RequestMapping("api/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @Operation(summary = "Registrar una venta",
            description = "Crea una nueva venta con su cliente, método de pago y estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La venta se registró correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o regla de negocio incumplida")
    })
    @PostMapping
    public ResponseEntity<VentaDtoResponse> guardar(@Valid @RequestBody VentaDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardar(dto));
    }

    //http://localhost:8080/api/venta
    @Operation(summary = "Listar todas las ventas",
            description = "Obtiene todas las ventas registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de ventas obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<List<VentaDtoResponse>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    //http://localhost:8080/api/venta/2
    @Operation(summary = "Buscar una venta por ID",
            description = "Obtiene la información de una venta específica a partir de su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "No existe una venta con ese ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaDtoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscarId(id));
    }

    //http://localhost:8080/api/venta/filtroDocumento?documento=1098765432
    @Operation(summary = "Filtrar ventas por documento del cliente",
            description = "Obtiene todas las ventas asociadas al número de documento indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ventas del cliente obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe un cliente con ese documento")
    })
    @GetMapping("/filtroDocumento")
    public ResponseEntity<List<VentaDtoResponse>> buscarDocumento(@RequestParam Long documento) {
        return ResponseEntity.ok(ventaService.buscarDocumento(documento));
    }

    @Operation(summary = "Eliminar una venta",
            description = "Elimina del sistema la venta correspondiente al ID indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "La venta se eliminó correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe una venta con ese ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Actualizar una venta",
            description = "Modifica los datos de una venta existente a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La venta se actualizó correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o regla de negocio incumplida"),
            @ApiResponse(responseCode = "404", description = "No existe una venta con ese ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VentaDtoResponse> actualizar(@Valid @RequestBody VentaDtoRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(ventaService.actualizar(dto, id));
    }
}