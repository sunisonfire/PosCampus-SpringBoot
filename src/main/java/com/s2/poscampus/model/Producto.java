package com.s2.poscampus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String marca;
    @Column(nullable = false)
    private Integer stock;
    @Column(name = "precio_compra", nullable = false)
    private BigDecimal precioCompra;
    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta;
}