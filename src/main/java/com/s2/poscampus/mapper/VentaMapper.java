package com.s2.poscampus.mapper;

import com.s2.poscampus.dto.request.VentaDtoRequest;
import com.s2.poscampus.dto.response.VentaDtoResponse;
import com.s2.poscampus.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {

    public VentaDtoResponse entityToDto(Venta venta) {
        if (venta == null) return null;
        return new VentaDtoResponse(venta.getId(), venta.getDocumentoCliente(), venta.getNombreCompleto(), venta.getTotal());
    }

    public Venta DtoToEntity(VentaDtoRequest dto) {
        if (dto == null) return null;
        Venta v = new Venta();
        v.setDocumentoCliente(dto.documentoCliente());
        v.setNombreCompleto(dto.nombreCompleto());
        v.setTotal(dto.total());
        return v;
    }

    public void updateEntityToDto(Venta venta, VentaDtoRequest dto) {
        venta.setDocumentoCliente(dto.documentoCliente());
        venta.setNombreCompleto(dto.nombreCompleto());
        venta.setTotal(dto.total());
    }
}