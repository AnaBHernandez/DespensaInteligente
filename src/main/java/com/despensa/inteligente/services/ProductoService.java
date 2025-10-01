package com.despensa.inteligente.services;

import java.util.Calendar;
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
        // Por ahora retorna null, aquí integrarías la API externa
        // TODO: Integrar con API de productos (Open Food Facts, UPC Database, etc.)
        return null;
    }

    public List<Producto> getProductosProximosACaducar() {
        List<Producto> todosLosProductos = productoRepository.findAll();
        Date fechaLimite = getFechaLimite(3); // 3 días antes
        
        return todosLosProductos.stream()
                .filter(producto -> producto.getFechaExpiracion() != null)
                .filter(producto -> producto.getFechaExpiracion().before(fechaLimite))
                .collect(Collectors.toList());
    }

    private Date getFechaLimite(int diasAntes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, diasAntes);
        return calendar.getTime();
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