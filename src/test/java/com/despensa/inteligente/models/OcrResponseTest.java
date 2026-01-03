package com.despensa.inteligente.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class OcrResponseTest {

    @Test
    void testGettersAndSetters() {
        OcrResponse response = new OcrResponse();
        String texto = "EXP 25/12/2025";
        LocalDate fecha = LocalDate.of(2025, 12, 25);
        String mensaje = "Fecha encontrada";
        double confianza = 0.95;

        response.setTextoExtraido(texto);
        response.setFechaExtraida(fecha);
        response.setFechaValida(true);
        response.setMensaje(mensaje);
        response.setConfianza(confianza);

        assertEquals(texto, response.getTextoExtraido());
        assertEquals(fecha, response.getFechaExtraida());
        assertTrue(response.isFechaValida());
        assertEquals(mensaje, response.getMensaje());
        assertEquals(confianza, response.getConfianza());
    }

    @Test
    void testConstructor() {
        String texto = "EXP 25/12/2025";
        LocalDate fecha = LocalDate.of(2025, 12, 25);
        String mensaje = "Fecha encontrada";
        double confianza = 0.95;

        OcrResponse response = new OcrResponse(texto, fecha, true, mensaje, confianza);

        assertEquals(texto, response.getTextoExtraido());
        assertEquals(fecha, response.getFechaExtraida());
        assertTrue(response.isFechaValida());
        assertEquals(mensaje, response.getMensaje());
        assertEquals(confianza, response.getConfianza());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate fecha = LocalDate.of(2025, 12, 25);
        OcrResponse response1 = new OcrResponse("texto", fecha, true, "msg", 0.9);
        OcrResponse response2 = new OcrResponse("texto", fecha, true, "msg", 0.9);
        OcrResponse response3 = new OcrResponse("otro texto", fecha, true, "msg", 0.9);

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
        assertNotEquals(response1, null);
        assertNotEquals(response1, new Object());
    }
}