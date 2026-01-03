package com.despensa.inteligente.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductoTest {

    @Test
    void testProductoGettersAndSetters() {
        Producto producto = new Producto();
        Long id = 1L;
        String nombre = "Leche";
        String descripcion = "Leche entera";
        BigDecimal precio = new BigDecimal("1.50");
        int cantidad = 2;
        LocalDate fecha = LocalDate.of(2024, 12, 31);
        String codigoBarras = "123456789";
        String marca = "Lactosa";
        String categoria = "Lácteos";

        producto.setId(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);
        producto.setFechaExpiracion(fecha);
        producto.setCodigoBarras(codigoBarras);
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        assertNotNull(producto);
        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(descripcion, producto.getDescripcion());
        assertEquals(precio, producto.getPrecio());
        assertEquals(cantidad, producto.getCantidad());
        assertEquals(fecha, producto.getFechaExpiracion());
        assertEquals(codigoBarras, producto.getCodigoBarras());
        assertEquals(marca, producto.getMarca());
        assertEquals(categoria, producto.getCategoria());
    }
}