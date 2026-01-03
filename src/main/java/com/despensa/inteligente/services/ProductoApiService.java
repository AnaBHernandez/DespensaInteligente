package com.despensa.inteligente.services;

import com.despensa.inteligente.models.Producto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate; // Import LocalDate

@Service
public class ProductoApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductoApiService() {
        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        return factory;
    }
    
    // API de Open Food Facts (gratuita)
    private static final String OPEN_FOOD_FACTS_API = "https://world.openfoodfacts.org/api/v0/product/";
    
    // API alternativa UPC Database (gratuita con límites)
    private static final String UPC_DATABASE_API = "https://api.upcitemdb.com/prod/trial/lookup";

    public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
        try {
            // Intentar primero con Open Food Facts
            Producto producto = buscarEnOpenFoodFacts(codigoBarras);
            if (producto != null) {
                return producto;
            }
            
            // Si no se encuentra, intentar con UPC Database
            return buscarEnUpcDatabase(codigoBarras);
            
        } catch (RestClientException e) {
            System.err.println("Error al buscar producto (HTTP): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
            return null;
        }
    }

    private Producto buscarEnOpenFoodFacts(String codigoBarras) {
        try {
            String url = OPEN_FOOD_FACTS_API + codigoBarras + ".json";
            String response = restTemplate.getForObject(url, String.class);

            if (response == null || response.isBlank()) {
                return null;
            }

            JsonNode root = objectMapper.readTree(response);

            if (root.has("status") && root.get("status").asInt() == 1) {
                JsonNode product = root.get("product");

                Producto producto = new Producto();
                producto.setCodigoBarras(codigoBarras);

                // Información básica
                if (product.has("product_name")) {
                    producto.setNombre(product.get("product_name").asText());
                }
                if (product.has("brands")) {
                    producto.setMarca(product.get("brands").asText());
                }
                if (product.has("categories")) {
                    producto.setCategoria(product.get("categories").asText());
                }
                if (product.has("ingredients_text")) {
                    producto.setDescripcion(product.get("ingredients_text").asText());
                }

                // Precio (si está disponible)
                if (product.has("price")) {
                    try {
                        producto.setPrecio(new BigDecimal(product.get("price").asText()));
                    } catch (Exception e) {
                        producto.setPrecio(BigDecimal.ZERO);
                    }
                } else {
                    producto.setPrecio(BigDecimal.ZERO);
                }

                producto.setCantidad(1);
                producto.setFechaExpiracion(null);

                return producto;
            }
            
        } catch (Exception e) {
            System.err.println("Error con Open Food Facts: " + e.getMessage());
        }
        
        return null;
    }

    private Producto buscarEnUpcDatabase(String codigoBarras) {
        try {
            String url = UPC_DATABASE_API + "?upc=" + codigoBarras;
            String response = restTemplate.getForObject(url, String.class);
            
            JsonNode root = objectMapper.readTree(response);
            
            if (root.has("items") && root.get("items").size() > 0) {
                JsonNode item = root.get("items").get(0);
                
                Producto producto = new Producto();
                producto.setCodigoBarras(codigoBarras);
                
                if (item.has("title")) {
                    producto.setNombre(item.get("title").asText());
                }
                if (item.has("brand")) {
                    producto.setMarca(item.get("brand").asText());
                }
                if (item.has("description")) {
                    producto.setDescripcion(item.get("description").asText());
                }
                
                // Precio por defecto
                producto.setPrecio(BigDecimal.ZERO);
                producto.setCantidad(1);

                // Establecer fecha de expiración por defecto a null
                producto.setFechaExpiracion(null);
                
                return producto;
            }
            
        } catch (Exception e) {
            System.err.println("Error con UPC Database: " + e.getMessage());
        }
        
        return null;
    }
}
