package com.despensa.inteligente.models;

import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ProductoTest {

    @Test
    public void testProductoGettersAndSetters() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del Producto 1");
        producto.setPrecio(new BigDecimal("10.50"));
        producto.setCantidad(100);
        producto.setFechaExpiracion(new Date());

        assertEquals(1L, producto.getId());
        assertEquals("Producto 1", producto.getNombre());
        assertEquals("Descripción del Producto 1", producto.getDescripcion());
        assertEquals(new BigDecimal("10.50"), producto.getPrecio());
        assertEquals(100, producto.getCantidad());
        assertNotNull(producto.getFechaExpiracion());
    }
}