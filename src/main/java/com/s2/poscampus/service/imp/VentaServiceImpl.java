package com.s2.poscampus.service.imp;

import com.s2.poscampus.dto.request.VentaDtoRequest;
import com.s2.poscampus.dto.response.VentaDtoResponse;
import lombok.RequiredArgsConstructor;
import com.s2.poscampus.mapper.VentaMapper;
import com.s2.poscampus.model.Venta;
import org.springframework.stereotype.Service;
import com.s2.poscampus.repository.VentaRepository;
import com.s2.poscampus.service.VentaService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VentaServiceImpl implements VentaService {
    private final VentaMapper ventaMapper;
    private final VentaRepository ventaRepository;

    @Override
    public VentaDtoResponse guardar(VentaDtoRequest dto) {
        Venta v = ventaMapper.DtoToEntity(dto);
        Venta ventaRespuesta = ventaRepository.save(v);
        return ventaMapper.entityToDto(ventaRespuesta);
    }

    @Override
    public VentaDtoResponse actualizar(VentaDtoRequest dto, Long id) {
        Venta v = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Error, venta a actualizar no encontrada"));
        ventaMapper.updateEntityToDto(v, dto);
        Venta ventaActualizada = ventaRepository.save(v);
        return ventaMapper.entityToDto(ventaActualizada);
    }

    @Override
    public void eliminar(Long id) {
        Venta v = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("La venta a eliminar no existe"));
        ventaRepository.delete(v);
    }

    @Override
    public List<VentaDtoResponse> listar() {
        return ventaRepository.findAll().stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public VentaDtoResponse buscarId(Long id) {
        return ventaMapper.entityToDto(
                ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe dicha venta")));
    }

    @Override
    public List<VentaDtoResponse> buscarDocumento(Long documentoCliente) {
        return ventaRepository.findByDocumentoCliente(documentoCliente).stream()
                .map(ventaMapper::entityToDto).toList();
    }
}