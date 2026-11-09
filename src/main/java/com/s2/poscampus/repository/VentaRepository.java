package com.s2.poscampus.repository;

import com.s2.poscampus.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByDocumentoCliente(Long documentoCliente);
}
