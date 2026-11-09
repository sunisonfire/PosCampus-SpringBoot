package com.s2.poscampus.controller;

import com.s2.poscampus.dto.request.DetalleVentaDtoRequest;
import com.s2.poscampus.dto.response.DetalleVentaDtoResponse;
import com.s2.poscampus.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Detalle Venta Controller",
        description = "Gestiona los detalles (productos, cantidades y subtotales) de cada venta")
@Validated
@RestController
@RequestMapping("api/detalleVenta")
@RequiredArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    @Operation(summary = "Registrar un detalle de venta",
            description = "Crea un nuevo detalle asociando un producto y una cantidad a una venta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El detalle de venta se registró correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o regla de negocio incumplida (ej. stock insuficiente)"),
            @ApiResponse(responseCode = "404", description = "La venta o el producto indicado no existe")
    })
    @PostMapping
    public ResponseEntity<DetalleVentaDtoResponse> guardar(@Valid @RequestBody DetalleVentaDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaService.guardar(dto));
    }

    //http://localhost:8080/api/detalleVenta
    @Operation(summary = "Listar todos los detalles de venta",
            description = "Obtiene todos los detalles de venta registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<List<DetalleVentaDtoResponse>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    //http://localhost:8080/api/detalleVenta/1
    @Operation(summary = "Buscar un detalle de venta por ID",
            description = "Obtiene un detalle de venta específico a partir de su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de venta encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un detalle de venta con ese ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDtoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.buscarId(id));
    }

    //http://localhost:8080/api/detalleVenta/filtroVenta?ventaId=1
    @Operation(summary = "Filtrar detalles por venta",
            description = "Obtiene todos los detalles que pertenecen a la venta indicada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de la venta obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "La venta indicada no existe")
    })
    @GetMapping("/filtroVenta")
    public ResponseEntity<List<DetalleVentaDtoResponse>> buscarVenta(@RequestParam Long ventaId) {
        return ResponseEntity.ok(detalleVentaService.buscarVenta(ventaId));
    }

    //http://localhost:8080/api/detalleVenta/filtroProducto?productoId=1
    @Operation(summary = "Filtrar detalles por producto",
            description = "Obtiene todos los detalles de venta en los que aparece el producto indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles del producto obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "El producto indicado no existe")
    })
    @GetMapping("/filtroProducto")
    public ResponseEntity<List<DetalleVentaDtoResponse>> buscarProducto(@RequestParam Long productoId) {
        return ResponseEntity.ok(detalleVentaService.buscarProducto(productoId));
    }

    @Operation(summary = "Actualizar un detalle de venta",
            description = "Modifica los datos de un detalle de venta existente a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El detalle de venta se actualizó correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o regla de negocio incumplida"),
            @ApiResponse(responseCode = "404", description = "No existe un detalle de venta con ese ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDtoResponse> actualizar(@Valid @RequestBody DetalleVentaDtoRequest dto,
                                                              @PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.actualizar(dto, id));
    }

    @Operation(summary = "Eliminar un detalle de venta",
            description = "Elimina del sistema el detalle de venta correspondiente al ID indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "El detalle de venta se eliminó correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe un detalle de venta con ese ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}