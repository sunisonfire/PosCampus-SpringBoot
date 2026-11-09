package com.s2.poscampus.service;

import com.s2.poscampus.dto.request.DetalleVentaDtoRequest;
import com.s2.poscampus.dto.response.DetalleVentaDtoResponse;

import java.util.List;

public interface DetalleVentaService {
    DetalleVentaDtoResponse guardar(DetalleVentaDtoRequest dto);
    DetalleVentaDtoResponse actualizar(DetalleVentaDtoRequest dto, Long id);
    void eliminar(Long id);
    List<DetalleVentaDtoResponse> listar();
    DetalleVentaDtoResponse buscarId(Long id);
    List<DetalleVentaDtoResponse> buscarVenta(Long ventaId);
    List<DetalleVentaDtoResponse> buscarProducto(Long productoId);
}