# 📱 Referencia de API - Despensa Inteligente

## 🌐 Base URL
```
http://localhost:8080
```

## 📋 Endpoints Disponibles

### **CRUD de Productos**

#### **GET /productos**
Obtener todos los productos
```bash
curl http://localhost:8080/productos
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "nombre": "Leche",
    "descripcion": "Leche entera",
    "precio": 1.50,
    "cantidad": 2,
    "fechaExpiracion": "2024-12-31",
    "codigoBarras": "123456789",
    "marca": "Lactosa",
    "categoria": "Lácteos"
  }
]
```

#### **GET /productos/{id}**
Obtener producto por ID
```bash
curl http://localhost:8080/productos/1
```

**Respuesta:**
```json
{
  "id": 1,
  "nombre": "Leche",
  "descripcion": "Leche entera",
  "precio": 1.50,
  "cantidad": 2,
  "fechaExpiracion": "2024-12-31",
  "codigoBarras": "123456789",
  "marca": "Lactosa",
  "categoria": "Lácteos"
}
```

#### **POST /productos**
Crear nuevo producto
```bash
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Leche",
    "descripcion": "Leche entera",
    "precio": 1.50,
    "cantidad": 2,
    "fechaExpiracion": "2024-12-31",
    "codigoBarras": "123456789",
    "marca": "Lactosa",
    "categoria": "Lácteos"
  }'
```

#### **PUT /productos/{id}**
Actualizar producto existente
```bash
curl -X PUT http://localhost:8080/productos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Leche Desnatada",
    "descripcion": "Leche desnatada",
    "precio": 1.30,
    "cantidad": 3,
    "fechaExpiracion": "2024-12-31",
    "codigoBarras": "123456789",
    "marca": "Lactosa",
    "categoria": "Lácteos"
  }'
```

#### **DELETE /productos/{id}**
Eliminar producto
```bash
curl -X DELETE http://localhost:8080/productos/1
```

### **Funcionalidades Avanzadas**

#### **POST /productos/escanear-completo** ⭐ **PRINCIPAL**
Escaneo completo: código de barras + OCR para fecha
```bash
curl -X POST \
  http://localhost:8080/productos/escanear-completo \
  -F "codigoBarras=1234567890123" \
  -F "imagenFecha=@fecha_caducidad.jpg"
```

**Respuesta:**
```json
{
  "producto": {
    "id": null,
    "nombre": "Producto encontrado",
    "descripcion": "Descripción del producto",
    "precio": 2.50,
    "cantidad": 1,
    "fechaExpiracion": "2024-12-31",
    "codigoBarras": "1234567890123",
    "marca": "Marca",
    "categoria": "Categoría"
  },
  "ocrResponse": {
    "textoExtraido": "Fecha: 31/12/2024",
    "fechaExtraida": "2024-12-31",
    "fechaValida": true,
    "mensaje": "Fecha extraída exitosamente",
    "confianza": 0.95
  },
  "productoEncontrado": true,
  "fechaExtraida": true,
  "mensaje": "Producto encontrado y fecha extraída exitosamente"
}
```

#### **POST /productos/escanear**
Solo escaneo de código de barras
```bash
curl -X POST http://localhost:8080/productos/escanear \
  -H "Content-Type: application/json" \
  -d '"1234567890123"'
```

#### **POST /productos/ocr**
Solo procesamiento OCR de imagen
```bash
curl -X POST \
  http://localhost:8080/productos/ocr \
  -F "imagen=@fecha_caducidad.jpg"
```

**Respuesta:**
```json
{
  "textoExtraido": "Fecha: 31/12/2024",
  "fechaExtraida": "2024-12-31",
  "fechaValida": true,
  "mensaje": "Fecha extraída exitosamente",
  "confianza": 0.95
}
```

#### **GET /productos/alertas**
Productos próximos a caducar
```bash
curl http://localhost:8080/productos/alertas
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "nombre": "Leche",
    "descripcion": "Leche entera",
    "precio": 1.50,
    "cantidad": 2,
    "fechaExpiracion": "2024-01-15",
    "codigoBarras": "123456789",
    "marca": "Lactosa",
    "categoria": "Lácteos"
  }
]
```

#### **GET /productos/recetas**
Sugerencias de recetas
```bash
curl http://localhost:8080/productos/recetas
```

**Respuesta:**
```json
[
  "Tortilla de patatas con los huevos que caducan mañana",
  "Ensalada con los tomates próximos a caducar",
  "Smoothie con la fruta que está madura"
]
```

## 📊 Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| 200 | OK - Operación exitosa |
| 201 | Created - Recurso creado |
| 204 | No Content - Eliminación exitosa |
| 400 | Bad Request - Error en la petición |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error - Error del servidor |

## 🔧 Ejemplos de Uso Completos

### **Flujo Completo de Gestión de Producto**

1. **Crear producto manualmente:**
```bash
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Yogur",
    "descripcion": "Yogur natural",
    "precio": 0.80,
    "cantidad": 4,
    "fechaExpiracion": "2024-01-20",
    "codigoBarras": "987654321",
    "marca": "Danone",
    "categoria": "Lácteos"
  }'
```

2. **Ver todos los productos:**
```bash
curl http://localhost:8080/productos
```

3. **Ver alertas de productos próximos a caducar:**
```bash
curl http://localhost:8080/productos/alertas
```

4. **Obtener sugerencias de recetas:**
```bash
curl http://localhost:8080/productos/recetas
```

### **Flujo de Escaneo Automático**

1. **Escaneo completo con imagen:**
```bash
curl -X POST \
  http://localhost:8080/productos/escanear-completo \
  -F "codigoBarras=1234567890123" \
  -F "imagenFecha=@fecha_caducidad.jpg"
```

2. **Solo código de barras:**
```bash
curl -X POST http://localhost:8080/productos/escanear \
  -H "Content-Type: application/json" \
  -d '"1234567890123"'
```

3. **Solo OCR de imagen:**
```bash
curl -X POST \
  http://localhost:8080/productos/ocr \
  -F "imagen=@fecha_caducidad.jpg"
```

## 🎯 Casos de Uso Típicos

### **1. Gestión de Despensa**
- Crear productos manualmente
- Actualizar cantidades
- Eliminar productos consumidos

### **2. Prevención de Desperdicio**
- Ver alertas de productos próximos a caducar
- Obtener sugerencias de recetas
- Planificar comidas

### **3. Inventario Automático**
- Escanear códigos de barras
- Extraer fechas con OCR
- Automatizar registro de productos

## 🔍 Testing de la API

### **Usando Postman**
1. Importar colección de endpoints
2. Configurar base URL: `http://localhost:8080`
3. Probar cada endpoint

### **Usando curl**
```bash
# Test básico de conectividad
curl -I http://localhost:8080/productos

# Test de creación
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Test","precio":1.0,"cantidad":1}'
```

---

**¡API lista para usar!** 🚀
