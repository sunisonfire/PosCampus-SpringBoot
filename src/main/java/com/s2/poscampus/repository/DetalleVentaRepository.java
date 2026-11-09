package com.s2.poscampus.repository;

import com.s2.poscampus.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//al extenderse con jpa repository trae save, find by id, dind all y delete
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    //se presentan consultas a partir del nombre del metodo
    boolean existsByVentaId(Long id);
    List<DetalleVenta> findByVentaId(Long id);
    boolean existsByProductoId(Long id);
    List<DetalleVenta> findByProductoId(Long id);
}
