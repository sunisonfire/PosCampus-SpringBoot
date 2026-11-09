package com.s2.poscampus.service.imp;

import com.s2.poscampus.dto.request.DetalleVentaDtoRequest;
import com.s2.poscampus.dto.response.DetalleVentaDtoResponse;
import com.s2.poscampus.exception.BussinesRuleException;
import com.s2.poscampus.mapper.DetalleVentaMapper;
import com.s2.poscampus.mapper.ProductoMapper;
import com.s2.poscampus.mapper.VentaMapper;
import com.s2.poscampus.model.DetalleVenta;
import com.s2.poscampus.model.Producto;
import com.s2.poscampus.model.Venta;
import com.s2.poscampus.repository.DetalleVentaRepository;
import com.s2.poscampus.repository.ProductoRepository;
import com.s2.poscampus.repository.VentaRepository;
import com.s2.poscampus.service.DetalleVentaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private static final int MAX_PRODUCTOS_POR_DETALLE = 250;

    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaMapper detalleVentaMapper;
    private final VentaMapper ventaMapper;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public DetalleVentaDtoResponse guardar(DetalleVentaDtoRequest dto) {
        validarCantidad(dto);
        Venta venta = buscarVentaEntidad(dto.ventaId());
        Producto producto = buscarProductoEntidad(dto.productoId());
        DetalleVenta detalle = detalleVentaMapper.dtoToEntity(dto, venta, producto);
        return convertir(detalleVentaRepository.save(detalle));
    }

    @Override
    @Transactional
    public DetalleVentaDtoResponse actualizar(DetalleVentaDtoRequest dto, Long id) {
        validarCantidad(dto);
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el detalle de venta a actualizar con id " + id));
        Venta venta = buscarVentaEntidad(dto.ventaId());
        Producto producto = buscarProductoEntidad(dto.productoId());
        detalleVentaMapper.updateDetalleVentaFromDto(detalle, dto, venta, producto);
        return convertir(detalleVentaRepository.save(detalle));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el detalle de venta a eliminar con id " + id));
        detalleVentaRepository.delete(detalle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVentaDtoResponse> listar() {
        return detalleVentaRepository.findAll().stream().map(this::convertir).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleVentaDtoResponse buscarId(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el detalle de venta con id " + id));
        return convertir(detalle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVentaDtoResponse> buscarVenta(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId).stream().map(this::convertir).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVentaDtoResponse> buscarProducto(Long productoId) {
        return detalleVentaRepository.findByProductoId(productoId).stream().map(this::convertir).toList();
    }


    // Regla de negocio: máximo de productos por detalle (responde 400).
    // Ajusta "cantidad()" al nombre real del campo en tu DetalleVentaDtoRequest.
    private void validarCantidad(DetalleVentaDtoRequest dto) {
        if (dto.cantidad() > MAX_PRODUCTOS_POR_DETALLE) {
            throw new BussinesRuleException("Máximo " + MAX_PRODUCTOS_POR_DETALLE + " productos por detalle");
        }
    }

    private Venta buscarVentaEntidad(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la venta con id " + id));
    }

    private Producto buscarProductoEntidad(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id " + id));
    }

    private DetalleVentaDtoResponse convertir(DetalleVenta detalle) {
        return detalleVentaMapper.entityToDto(
                detalle,
                ventaMapper.entityToDto(detalle.getVenta()),
                productoMapper.entityToDtoFactura(detalle.getProducto()));
    }
}