package com.despensa.inteligente.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.despensa.inteligente.models.OcrResponse;
import com.despensa.inteligente.models.Producto;
import com.despensa.inteligente.models.ProductoCompletoResponse;
import com.despensa.inteligente.services.OcrService;
import com.despensa.inteligente.services.ProductoApiService;
import com.despensa.inteligente.services.ProductoService;

@RestController
@RequestMapping("/productos") // Define el endpoint base
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private OcrService ocrService;
    
    @Autowired
    private ProductoApiService productoApiService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    // Escanear código de barras y crear producto
    @PostMapping("/escanear")
    public ResponseEntity<Producto> escanearCodigoBarras(@RequestBody String codigoBarras) {
        try {
            Producto producto = productoService.buscarProductoPorCodigoBarras(codigoBarras);
            if (producto != null) {
                return ResponseEntity.ok(producto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoExistente = productoService.getProductoById(id);
        if (productoExistente.isPresent()) {
            producto.setId(id);
            Producto productoActualizado = productoService.saveProducto(producto);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener productos próximos a caducar
    @GetMapping("/alertas")
    public List<Producto> getProductosProximosACaducar() {
        return productoService.getProductosProximosACaducar();
    }

    // Obtener sugerencias de recetas
    @GetMapping("/recetas")
    public List<String> getSugerenciasRecetas() {
        return productoService.getSugerenciasRecetas();
    }

    // Procesar imagen para extraer fecha de caducidad
    @PostMapping("/ocr")
    public ResponseEntity<OcrResponse> procesarImagenFecha(@RequestParam("imagen") MultipartFile imagen) {
        try {
            if (imagen.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            byte[] imagenBytes = imagen.getBytes();
            OcrResponse respuesta = ocrService.extraerFechaDeImagen(imagenBytes);
            
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ENDPOINT COMBINADO: Código de barras + OCR para fecha
    @PostMapping("/escanear-completo")
    public ResponseEntity<ProductoCompletoResponse> escanearProductoCompleto(
            @RequestParam("codigoBarras") String codigoBarras,
            @RequestParam("imagenFecha") MultipartFile imagenFecha) {
        try {
            // 1. Buscar información del producto por código de barras
            Producto producto = productoApiService.buscarProductoPorCodigoBarras(codigoBarras);
            boolean productoEncontrado = producto != null;
            
            // 2. Extraer fecha de caducidad con OCR
            OcrResponse ocrResponse = null;
            boolean fechaExtraida = false;
            
            if (imagenFecha != null && !imagenFecha.isEmpty()) {
                byte[] imagenBytes = imagenFecha.getBytes();
                ocrResponse = ocrService.extraerFechaDeImagen(imagenBytes);
                fechaExtraida = ocrResponse.isFechaValida();
                
                // 3. Si se encontró el producto y se extrajo la fecha, combinarlos
                if (producto != null && fechaExtraida) {
                    producto.setFechaExpiracion(ocrResponse.getFechaExtraida());
                }
            }
            
            // 4. Crear respuesta
            String mensaje = "";
            if (productoEncontrado && fechaExtraida) {
                mensaje = "Producto encontrado y fecha extraída exitosamente";
            } else if (productoEncontrado && !fechaExtraida) {
                mensaje = "Producto encontrado, pero no se pudo extraer la fecha";
            } else if (!productoEncontrado && fechaExtraida) {
                mensaje = "Fecha extraída, pero producto no encontrado en base de datos";
            } else {
                mensaje = "No se pudo obtener información del producto ni extraer la fecha";
            }
            
            ProductoCompletoResponse respuesta = new ProductoCompletoResponse(
                producto, ocrResponse, productoEncontrado, fechaExtraida, mensaje
            );
            
            return ResponseEntity.ok(respuesta);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
