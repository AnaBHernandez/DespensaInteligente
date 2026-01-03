package com.despensa.inteligente.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoCompletoResponseTest {

    @Test
    void testGettersAndSetters() {
        ProductoCompletoResponse response = new ProductoCompletoResponse();
        Producto producto = new Producto();
        producto.setNombre("Leche");
        OcrResponse ocrResponse = new OcrResponse();
        ocrResponse.setFechaValida(true);
        String mensaje = "Todo OK";

        response.setProducto(producto);
        response.setOcrResponse(ocrResponse);
        response.setProductoEncontrado(true);
        response.setFechaExtraida(true);
        response.setMensaje(mensaje);

        assertEquals(producto, response.getProducto());
        assertEquals(ocrResponse, response.getOcrResponse());
        assertTrue(response.isProductoEncontrado());
        assertTrue(response.isFechaExtraida());
        assertEquals(mensaje, response.getMensaje());
    }

    @Test
    void testConstructor() {
        Producto producto = new Producto();
        producto.setNombre("Leche");
        OcrResponse ocrResponse = new OcrResponse();
        ocrResponse.setFechaValida(true);
        String mensaje = "Todo OK";

        ProductoCompletoResponse response = new ProductoCompletoResponse(producto, ocrResponse, true, true, mensaje);

        assertEquals(producto, response.getProducto());
        assertEquals(ocrResponse, response.getOcrResponse());
        assertTrue(response.isProductoEncontrado());
        assertTrue(response.isFechaExtraida());
        assertEquals(mensaje, response.getMensaje());
    }

    @Test
    void testEqualsAndHashCode() {
        Producto producto1 = new Producto();
        producto1.setId(1L);
        OcrResponse ocr1 = new OcrResponse();
        ocr1.setFechaValida(true);

        ProductoCompletoResponse response1 = new ProductoCompletoResponse(producto1, ocr1, true, true, "OK");
        ProductoCompletoResponse response2 = new ProductoCompletoResponse(producto1, ocr1, true, true, "OK");

        Producto producto2 = new Producto();
        producto2.setId(2L);
        ProductoCompletoResponse response3 = new ProductoCompletoResponse(producto2, ocr1, true, true, "OK");

        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }
}