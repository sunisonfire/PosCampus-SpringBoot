package com.s2.poscampus.controller;

import com.s2.poscampus.dto.request.ProductoDtoRequest;
import com.s2.poscampus.dto.response.ProductoDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.s2.poscampus.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Producto Controller",
        description = "Gestiona el catálogo de productos disponibles para la venta")
@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Registrar un producto",
            description = "Crea un nuevo producto en el catálogo con sus datos básicos (nombre, marca, precio, stock)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El producto se registró correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del producto inválidos o incompletos")
    })
    @PostMapping
    public ResponseEntity<ProductoDtoResponse> guardar(@Valid @RequestBody ProductoDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    //http://localhost:8080/api/producto
    @Operation(summary = "Listar todos los productos",
            description = "Obtiene todos los productos registrados en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<List<ProductoDtoResponse>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    //http://localhost:8080/api/producto/2
    @Operation(summary = "Buscar un producto por ID",
            description = "Obtiene la información de un producto específico a partir de su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un producto con ese ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarId(id));
    }

    //http://localhost:8080/api/producto/filtroMarca?nombre=Samsung
    @Operation(summary = "Filtrar productos por marca",
            description = "Obtiene todos los productos que pertenecen a la marca indicada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos de la marca obtenidos correctamente")
    })
    @GetMapping("/filtroMarca")
    public ResponseEntity<List<ProductoDtoResponse>> buscarMarca(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarMarca(nombre));
    }

    @Operation(summary = "Eliminar un producto",
            description = "Elimina del catálogo el producto correspondiente al ID indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "El producto se eliminó correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe un producto con ese ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Actualizar un producto",
            description = "Modifica los datos de un producto existente a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El producto se actualizó correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del producto inválidos"),
            @ApiResponse(responseCode = "404", description = "No existe un producto con ese ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> actualizar(@Valid @RequestBody ProductoDtoRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(productoService.actualizar(dto, id));
    }
}