package com.s2.poscampus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter

public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)  //no se cargan hasta que se usan(LAZY)
    @JoinColumn(nullable = false, name = "venta_id")
    private Venta venta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "producto_id")
    private Producto producto;
    @Column(nullable = false) //no puede existir sin venta o producto
    private Integer cantidad;
    @Column(nullable = false)
    private BigDecimal subtotal;
}