package com.s2.poscampus.mapper;

import com.s2.poscampus.dto.request.DetalleVentaDtoRequest;
import com.s2.poscampus.dto.response.DetalleVentaDtoResponse;
import com.s2.poscampus.dto.response.ProductoDtoResponseFactura;
import com.s2.poscampus.dto.response.VentaDtoResponse;
import com.s2.poscampus.model.DetalleVenta;
import com.s2.poscampus.model.Producto;
import com.s2.poscampus.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {
    public DetalleVentaDtoResponse entityToDto(DetalleVenta detalle, VentaDtoResponse ventaDto, ProductoDtoResponseFactura productoDto){
        if(detalle==null || ventaDto==null || productoDto==null) return null;
        return new DetalleVentaDtoResponse(detalle.getId(), ventaDto, productoDto, detalle.getCantidad(), detalle.getSubtotal());

    }

    public DetalleVenta dtoToEntity(DetalleVentaDtoRequest dto, Venta venta, Producto producto){
        if(dto==null || venta==null || producto==null) return null;
        DetalleVenta detalle=new DetalleVenta();
        detalle.setCantidad(dto.cantidad());
        detalle.setSubtotal(dto.subtotal());
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        return detalle;

    }

    public void updateDetalleVentaFromDto(DetalleVenta detalle, DetalleVentaDtoRequest dto, Venta venta, Producto producto) {
        if (detalle == null || dto == null || venta == null || producto == null) return;
        detalle.setCantidad(dto.cantidad());
        detalle.setSubtotal(dto.subtotal());
        detalle.setVenta(venta);         // <- faltaba
        detalle.setProducto(producto);   // <- faltaba
    }
}
