package com.s2.poscampus.repository;

import com.s2.poscampus.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByMarca(String nombre);

    List<Producto> findByNombre(String nombre);
// select * from producto where nombre = 'TV';

    List<Producto> findByNombreContaining(String texto);
// select * from producto where nombre like '%TV%';

    List<Producto> findByMarcaStartingWith(String prefijo);
// select * from producto where marca like 'Sam%';


    List<Producto> findByStockLessThan(int stock);

    List<Producto> findByPrecioVentaGreaterThanEqual(BigDecimal precio);

    List<Producto> findByPrecioVentaBetween(BigDecimal min, BigDecimal max);

    List<Producto> findByMarcaAndStockGreaterThan(String marca, int stock);
// select * from producto where marca = 'LG' and stock > 5;

    List<Producto> findByMarcaInOrderByPrecioVentaAsc(List<String> marcas);
// select * from producto where marca in ('LG','Samsung') order by precio_venta asc;

    List<Producto> findTop3ByOrderByPrecioVentaDesc();
// select * from producto order by precio_venta desc limit 3;
}
