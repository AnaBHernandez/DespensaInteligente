package com.despensa.inteligente.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.despensa.inteligente.models.Producto;
import com.despensa.inteligente.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ProductoApiService productoApiService; // Inyectar ProductoApiService

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
        return productoApiService.buscarProductoPorCodigoBarras(codigoBarras);
    }

    public List<Producto> getProductosProximosACaducar() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaLimite = hoy.plusDays(3); // Productos que caducan en los próximos 3 días

        return productoRepository.findByFechaExpiracionBetween(hoy, fechaLimite);
    }

    // Método para obtener productos caducados (opcional, pero útil)
    public List<Producto> getProductosCaducados() {
        LocalDate hoy = LocalDate.now();
        return productoRepository.findByFechaExpiracionBefore(hoy);
    }

    public List<String> getSugerenciasRecetas() {
        List<Producto> productosProximos = getProductosProximosACaducar();
        
        // Por ahora retorna sugerencias básicas
        // TODO: Integrar con API de recetas (Spoonacular, Edamam, etc.)
        return productosProximos.stream()
                .map(producto -> "Receta sugerida con " + producto.getNombre() + 
                     " (caduca el " + producto.getFechaExpiracion() + ")")
                .collect(Collectors.toList());
    }
}