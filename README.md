# 🏠 Despensa Inteligente

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Descripción

**Despensa Inteligente** es una aplicación backend desarrollada en Java con Spring Boot que combina escaneo de códigos de barras con OCR gratuito (Tesseract) para gestionar productos de despensa automáticamente y prevenir el desperdicio de alimentos.

## ✨ Características

### 🆓 **100% GRATUITO**
- **Open Food Facts API** - Sin límites, completamente gratuita
- **Tesseract OCR** - Open source, 100% gratuito
- **H2 Database** - Base de datos gratuita para desarrollo
- **Spring Boot** - Framework gratuito

### 🚀 **Funcionalidades**
- ✅ **CRUD completo** de productos
- ✅ **Escaneo de códigos de barras** con Open Food Facts
- ✅ **OCR con Tesseract** para extraer fechas de caducidad
- ✅ **Sistema de alertas** para productos próximos a caducar
- ✅ **Sugerencias de recetas** basadas en productos
- ✅ **API REST** completa con 10 endpoints

## 🛠️ Tecnologías

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en memoria
- **Tesseract OCR** - Reconocimiento óptico de caracteres
- **Open Food Facts API** - Base de datos de productos
- **Maven** - Gestión de dependencias

## 🚀 Instalación Rápida

### Requisitos
- Java 21
- Maven 3.8+

### Ejecutar
```bash
# Clonar repositorio
git clone https://github.com/TU-USUARIO/despensa-inteligente-backend.git
cd despensa-inteligente-backend

# Compilar y ejecutar
mvn clean compile
mvn spring-boot:run
```

### Acceder
- **API**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console

## 📱 Endpoints Principales

### CRUD de Productos
- `GET /productos` - Obtener todos los productos
- `GET /productos/{id}` - Obtener producto por ID
- `POST /productos` - Crear producto
- `PUT /productos/{id}` - Actualizar producto
- `DELETE /productos/{id}` - Eliminar producto

### Funcionalidades Avanzadas
- `POST /productos/escanear-completo` - **PRINCIPAL** - Escaneo completo (código + OCR)
- `POST /productos/escanear` - Solo código de barras
- `POST /productos/ocr` - Solo OCR
- `GET /productos/alertas` - Productos próximos a caducar
- `GET /productos/recetas` - Sugerencias de recetas

## 🔧 Ejemplo de Uso

### Crear producto
```bash
curl -X POST http://localhost:8081/productos \
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

### Escaneo completo
```bash
curl -X POST \
  http://localhost:8081/productos/escanear-completo \
  -F "codigoBarras=1234567890123" \
  -F "imagenFecha=@fecha_caducidad.jpg"
```

## 🗺️ Hoja de Ruta y Gestión del Proyecto

La evolución de **Despensa Inteligente** se gestiona de forma transparente en un tablero **Kanban**, demostrando la planificación a largo plazo y la priorización ágil.

- **Ver el Roadmap:** [Tablero de Proyectos de Despensa Inteligente](https://github.com/users/AnaBHernandez/projects/5)
- **Workflow:** Las tareas y errores (Issues) se mueven a través de las columnas (Backlog → Ready → In Progress) hasta ser resueltas.

## 📊 Estructura del Proyecto

```
src/
├── main/java/com/despensa/inteligente/
│   ├── controllers/          # Controladores REST
│   ├── models/              # Entidades y DTOs
│   ├── repository/          # Repositorios JPA
│   └── services/            # Lógica de negocio
└── resources/
    ├── application.properties
    └── static/index.html
```

## 🎯 Casos de Uso

1. **Gestión de Despensa** - Control automático de productos
2. **Prevención de Desperdicio** - Alertas de productos próximos a caducar
3. **Sugerencias de Recetas** - Ideas para usar productos antes de que caduquen
4. **Inventario Automático** - Escaneo rápido de productos

## 📈 Métricas

- **Líneas de código**: 800+ líneas
- **Clases implementadas**: 9 clases principales
- **Endpoints**: 10 endpoints REST
- **Integraciones**: 2 APIs gratuitas
- **Cobertura de pruebas**: Implementada

## 🏆 Logros

✅ **Proyecto 100% gratuito**  
✅ **Open Source** - Tesseract OCR  
✅ **Sin límites de API** - Open Food Facts  
✅ **Funcionalidad completa** - Todas las características  
✅ **Fácil despliegue** - Sin configuración compleja  
✅ **Escalable** - Fácil agregar más funcionalidades  

## 🚀 Próximos Pasos

1. **Frontend Web** - Interfaz de usuario
2. **Aplicación Móvil** - Para escanear códigos de barras
3. **Notificaciones** - Sistema de alertas por email/SMS
4. **API de recetas** - Integrar con Spoonacular o Edamam
5. **Base de datos** - Migrar a PostgreSQL para producción

## 📚 Documentación

- [🚀 Guía de Instalación](docs/installation.md)
- [📱 Referencia de API](docs/api-reference.md)
- [🔧 Solución de Problemas](docs/troubleshooting.md)
- [🏗️ Arquitectura del Sistema](docs/architecture.md)

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## 👨‍💻 Autor

**Ana Hernández** - [GitHub](https://github.com/TU-USUARIO)

## 🙏 Agradecimientos

- **Open Food Facts** - API gratuita de productos
- **Tesseract OCR** - OCR open source
- **Spring Boot** - Framework de desarrollo
- **Comunidad Java** - Soporte y documentación

---

**¡Tu despensa inteligente te está esperando!** 🏠✨