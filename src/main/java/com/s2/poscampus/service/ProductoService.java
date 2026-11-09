package com.s2.poscampus.service;

import com.s2.poscampus.dto.request.ProductoDtoRequest;
import com.s2.poscampus.dto.response.ProductoDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {
    ProductoDtoResponse guardar(ProductoDtoRequest dto);
    ProductoDtoResponse actualizar(ProductoDtoRequest dto, Long id);
    void eliminar(Long id);
    List<ProductoDtoResponse> listar();
    ProductoDtoResponse buscarId(Long id);
    List<ProductoDtoResponse> buscarMarca(String nombre);
}
