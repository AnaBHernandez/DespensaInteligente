package com.despensa.inteligente.controllers;

import com.despensa.inteligente.models.Producto;
import com.despensa.inteligente.services.OcrService;
import com.despensa.inteligente.services.ProductoApiService;
import com.despensa.inteligente.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private OcrService ocrService;

    @MockBean
    private ProductoApiService productoApiService;

    @Test
    void testGetAllProductosReturnsEmptyList() throws Exception {
        when(productoService.getAllProductos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/productos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
