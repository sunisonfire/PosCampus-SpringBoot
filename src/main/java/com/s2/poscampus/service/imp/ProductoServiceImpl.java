package com.s2.poscampus.service.imp;

import com.s2.poscampus.dto.request.ProductoDtoRequest;
import com.s2.poscampus.dto.response.ProductoDtoResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.s2.poscampus.mapper.ProductoMapper;
import com.s2.poscampus.model.Producto;
import org.springframework.stereotype.Service;
import com.s2.poscampus.repository.ProductoRepository;
import com.s2.poscampus.service.ProductoService;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ProductoServiceImpl implements ProductoService {
    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;
    @Override
    public ProductoDtoResponse guardar(ProductoDtoRequest dto) {
        //cast: convierto el dto en una entity
        Producto p=productoMapper.DtoToEntity(dto);
        Producto productoRespuesta=productoRepository.save(p);
        return productoMapper.entityToDto(productoRespuesta);
        //return productoMapper.entityToDto(productoRepository.save(productoMapper.DtoToEntity(dto)));
    }

    @Override
    public ProductoDtoResponse actualizar(ProductoDtoRequest dto, Long id) {
        Producto p=productoRepository.findById(id).orElseThrow(()-> new RuntimeException("Error, producto a actualizar no encontrado"));
        productoMapper.updateEntityFromDto(p,dto);
        Producto productoActualizado=productoRepository.save(p);
        return productoMapper.entityToDto((productoActualizado));
    }

    @Override
    public void eliminar(Long id) {
        Producto p= productoRepository.findById(id).orElseThrow(()-> new RuntimeException("El producto a eliminar no existe vv!"));
        productoRepository.delete(p);
    }

    @Override
    public List<ProductoDtoResponse> listar() {
        return productoRepository.findAll().stream().map(productoMapper::entityToDto).toList();
    }

    @Override public ProductoDtoResponse buscarId(Long id) { return productoMapper.entityToDto( productoRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("El producto con ID " + id + " no existe")) ); }

    @Override
    public List<ProductoDtoResponse> buscarMarca(String nombre) {
        return productoRepository.findByMarca(nombre).stream().map(productoMapper::entityToDto).toList();
    }
}
