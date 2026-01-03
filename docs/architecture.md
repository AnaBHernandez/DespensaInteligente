# 🏗️ Arquitectura del Sistema - Despensa Inteligente

## 📊 Diagrama de Clases

```mermaid
classDiagram
    class ProductoController {
        -ProductoService productoService
        -OcrService ocrService
        -ProductoApiService productoApiService
        +getAllProductos(): List~Producto~
        +getProductoById(Long id) Producto
        +createProducto(Producto producto) Producto
        +updateProducto(Long id, Producto producto) Producto
        +deleteProducto(Long id) void
        +escanearProductoCompleto(String codigoBarras, MultipartFile imagenFecha) ProductoCompletoResponse
        +escanearProducto(String codigoBarras) Producto
        +procesarOcr(MultipartFile imagen) OcrResponse
        +getProductosProximosACaducar() List~Producto~
        +getSugerenciasRecetas() List~String~
    }

    class ProductoService {
        -ProductoRepository productoRepository
        +getAllProductos(): List~Producto~
        +getProductoById(Long id) Producto
        +createProducto(Producto producto) Producto
        +updateProducto(Long id, Producto producto) Producto
        +deleteProducto(Long id) void
        +buscarProductoPorCodigoBarras(String codigoBarras) Producto
        +getProductosProximosACaducar() List~Producto~
        +getSugerenciasRecetas() List~String~
    }

    class OcrService {
        +extraerFechaDeImagen(byte[] imagenBytes) OcrResponse
        -extraerFechaDelTexto(String texto) Date
    }

    class ProductoApiService {
        +buscarProductoPorCodigoBarras(String codigoBarras) Producto
    }

    class ProductoRepository {
        +findAll() List~Producto~
        +findById(Long id) Optional~Producto~
        +save(Producto producto) Producto
        +deleteById(Long id) void
    }

    class Producto {
        -Long id
        -String nombre
        -String descripcion
        -BigDecimal precio
        -int cantidad
        -Date fechaExpiracion
        -String codigoBarras
        -String marca
        -String categoria
    }

    class OcrResponse {
        -String textoExtraido
        -Date fechaExtraida
        -boolean fechaValida
        -String mensaje
        -double confianza
    }

    class ProductoCompletoResponse {
        -Producto producto
        -OcrResponse ocrResponse
        -boolean productoEncontrado
        -boolean fechaExtraida
        -String mensaje
    }

    %% Relaciones
    ProductoController --> ProductoService : uses
    ProductoController --> OcrService : uses
    ProductoController --> ProductoApiService : uses
    ProductoService --> ProductoRepository : uses
    ProductoRepository --> Producto : manages
    OcrService --> OcrResponse : creates
    ProductoApiService --> Producto : creates
    ProductoCompletoResponse --> Producto : contains
    ProductoCompletoResponse --> OcrResponse : contains
```

## 🔄 Flujo de Datos

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as ProductoController
    participant Service as ProductoService
    participant OCR as OcrService
    participant API as ProductoApiService
    participant DB as Base de Datos

    Note over Client,DB: Escaneo Completo de Producto

    Client->>Controller: POST /escanear-completo
    Note right of Client: codigoBarras + imagenFecha

    Controller->>API: buscarProductoPorCodigoBarras()
    API-->>Controller: Producto (si existe)

    Controller->>OCR: extraerFechaDeImagen()
    OCR-->>Controller: OcrResponse

    alt Producto encontrado y fecha extraída
        Controller->>Service: createProducto()
        Service->>DB: save()
        DB-->>Service: Producto guardado
        Service-->>Controller: Producto
    end

    Controller-->>Client: ProductoCompletoResponse
```

## 🏗️ Arquitectura del Sistema

```mermaid
graph TB
    subgraph "Frontend (Futuro)"
        UI[Interfaz Web]
        Mobile[App Móvil]
    end

    subgraph "Backend - Spring Boot"
        subgraph "Controllers"
            PC[ProductoController]
        end
        
        subgraph "Services"
            PS[ProductoService]
            OS[OcrService]
            PAS[ProductoApiService]
        end
        
        subgraph "Repository"
            PR[ProductoRepository]
        end
        
        subgraph "Models"
            P[Producto]
            OR[OcrResponse]
            PCR[ProductoCompletoResponse]
        end
    end

    subgraph "Base de Datos"
        H2[(H2 Database)]
        MySQL[(MySQL - Producción)]
    end

    subgraph "APIs Externas"
        OFF[Open Food Facts API]
        Tesseract[Tesseract OCR]
    end

    %% Conexiones
    UI --> PC
    Mobile --> PC
    
    PC --> PS
    PC --> OS
    PC --> PAS
    
    PS --> PR
    PR --> H2
    PR --> MySQL
    
    OS --> Tesseract
    PAS --> OFF
    
    PS --> P
    OS --> OR
    PC --> PCR
```

## 📱 Endpoints y Funcionalidades

```mermaid
graph LR
    subgraph "CRUD Básico"
        GET1[GET /productos]
        GET2[GET /productos/{id}]
        POST1[POST /productos]
        PUT1[PUT /productos/{id}]
        DELETE1[DELETE /productos/{id}]
    end

    subgraph "Funcionalidades Avanzadas"
        SCAN[POST /escanear-completo]
        BARCODE[POST /escanear]
        OCR[POST /ocr]
        ALERTS[GET /alertas]
        RECIPES[GET /recetas]
    end

    subgraph "Servicios"
        PS[ProductoService]
        OS[OcrService]
        PAS[ProductoApiService]
    end

    GET1 --> PS
    GET2 --> PS
    POST1 --> PS
    PUT1 --> PS
    DELETE1 --> PS

    SCAN --> PS
    SCAN --> OS
    SCAN --> PAS
    BARCODE --> PAS
    OCR --> OS
    ALERTS --> PS
    RECIPES --> PS
```

## 🎯 Patrones de Diseño Utilizados

### **1. MVC (Model-View-Controller)**
- **Model**: Entidades (`Producto`, `OcrResponse`, `ProductoCompletoResponse`)
- **View**: Respuestas JSON de la API
- **Controller**: `ProductoController` maneja las peticiones HTTP

### **2. Repository Pattern**
- `ProductoRepository` abstrae el acceso a datos
- Facilita el cambio de base de datos
- Centraliza las operaciones CRUD

### **3. Service Layer Pattern**
- `ProductoService` contiene la lógica de negocio
- `OcrService` maneja el procesamiento OCR
- `ProductoApiService` gestiona integraciones externas

### **4. DTO Pattern**
- `OcrResponse` para respuestas del OCR
- `ProductoCompletoResponse` para respuestas complejas
- Separación entre entidades y DTOs

## 🔧 Componentes del Sistema

### **Controllers**
- **ProductoController**: Maneja todas las peticiones HTTP
- Endpoints REST para CRUD y funcionalidades avanzadas
- Validación de entrada y manejo de errores

### **Services**
- **ProductoService**: Lógica de negocio para productos
- **OcrService**: Procesamiento de imágenes con Tesseract
- **ProductoApiService**: Integración con Open Food Facts

### **Repository**
- **ProductoRepository**: Acceso a datos con Spring Data JPA
- Operaciones CRUD automáticas
- Consultas personalizadas si es necesario

### **Models**
- **Producto**: Entidad principal del sistema
- **OcrResponse**: Respuesta del procesamiento OCR
- **ProductoCompletoResponse**: Respuesta combinada

## 🌐 Integraciones Externas

### **Open Food Facts API**
- **Propósito**: Obtener información de productos por código de barras
- **Gratuito**: Sin límites de uso
- **Datos**: Nombre, descripción, marca, categoría, precio

### **Tesseract OCR**
- **Propósito**: Extraer fechas de caducidad de imágenes
- **Gratuito**: Open source
- **Idiomas**: Español e inglés
- **Precisión**: Configurable según necesidades

### **H2 Database**
- **Desarrollo**: Base de datos en memoria
- **Producción**: MySQL o PostgreSQL
- **Ventajas**: Fácil configuración, sin instalación

## 📊 Flujo de Datos Típico

### **1. Creación Manual de Producto**
```
Cliente → ProductoController → ProductoService → ProductoRepository → H2 Database
```

### **2. Escaneo de Código de Barras**
```
Cliente → ProductoController → ProductoApiService → Open Food Facts API → Producto
```

### **3. Procesamiento OCR**
```
Cliente → ProductoController → OcrService → Tesseract → OcrResponse
```

### **4. Escaneo Completo**
```
Cliente → ProductoController → [ProductoApiService + OcrService] → ProductoCompletoResponse
```

## 🚀 Escalabilidad

### **Horizontal**
- Múltiples instancias de la aplicación
- Load balancer para distribución de carga
- Base de datos replicada

### **Vertical**
- Aumentar recursos del servidor
- Optimizar consultas de base de datos
- Cache de respuestas frecuentes

### **Futuras Mejoras**
- **Cache**: Redis para respuestas frecuentes
- **Queue**: RabbitMQ para procesamiento asíncrono
- **Monitoring**: Prometheus + Grafana
- **Logs**: ELK Stack (Elasticsearch, Logstash, Kibana)

## 🔒 Seguridad

### **Implementado**
- Validación de entrada en controladores
- Manejo de errores sin exposición de detalles
- Configuración de CORS si es necesario

### **Futuras Mejoras**
- **Autenticación**: JWT tokens
- **Autorización**: Roles y permisos
- **HTTPS**: Certificados SSL
- **Rate Limiting**: Límites de peticiones

---

**¡Arquitectura bien estructurada y escalable!** 🏗️
