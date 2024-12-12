package com.despensa.inteligente.controllers;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.despensa.inteligente.models.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateProducto() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del Producto 1");
        producto.setPrecio(new BigDecimal("10.50"));
        producto.setCantidad(100);
        producto.setFechaExpiracion(new Date()); // Asegúrate de establecer la fecha de expiración

        mockMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto 1"));
    }

    @Test
    public void testGetAllProductos() throws Exception {
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}