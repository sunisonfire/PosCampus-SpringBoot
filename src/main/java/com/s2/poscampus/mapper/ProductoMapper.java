package com.s2.poscampus.mapper;

import com.s2.poscampus.dto.request.ProductoDtoRequest;
import com.s2.poscampus.dto.response.ProductoDtoResponse;
import com.s2.poscampus.dto.response.ProductoDtoResponseFactura;
import com.s2.poscampus.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDtoResponse entityToDto(Producto producto) {
        if (producto == null) return null;
        return new ProductoDtoResponse(producto.getId(), producto.getNombre(), producto.getMarca(),
                producto.getStock(), producto.getPrecioCompra(), producto.getPrecioVenta());
    }

    // Lo usa DetalleVentaServiceImpl. AJUSTA los campos al record ProductoDtoResponseFactura (ver nota abajo)
    public ProductoDtoResponseFactura entityToDtoFactura(Producto producto) {
        if (producto == null) return null;
        return new ProductoDtoResponseFactura(producto.getId(), producto.getNombre(),
                producto.getMarca(), producto.getPrecioVenta());
    }

    public Producto DtoToEntity(ProductoDtoRequest dto) {
        if (dto == null) return null;
        Producto p = new Producto();
        p.setNombre(dto.nombre());
        p.setMarca(dto.marca());
        p.setStock(dto.stock());
        p.setPrecioCompra(dto.precioCompra());
        p.setPrecioVenta(dto.precioVenta());
        return p;
    }

    public void updateEntityFromDto(Producto producto, ProductoDtoRequest dto) {
        if (dto == null || producto == null) return;
        producto.setNombre(dto.nombre());        // antes: se asignaba a un "new Producto()" que se descartaba
        producto.setMarca(dto.marca());
        producto.setStock(dto.stock());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
    }
}