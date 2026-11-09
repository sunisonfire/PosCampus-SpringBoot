package com.s2.poscampus.service;

import com.s2.poscampus.dto.request.VentaDtoRequest;
import com.s2.poscampus.dto.response.VentaDtoResponse;

import java.util.List;

public interface VentaService {
    VentaDtoResponse guardar(VentaDtoRequest dto);
    VentaDtoResponse actualizar(VentaDtoRequest dto, Long id);
    void eliminar(Long id);
    List<VentaDtoResponse> listar();
    VentaDtoResponse buscarId(Long id);
    List<VentaDtoResponse> buscarDocumento(Long documentoCliente);
}