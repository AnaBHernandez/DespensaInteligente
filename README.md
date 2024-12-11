# Despensa Inteligente

## Descripción del Proyecto
Despensa Inteligente es una aplicación backend desarrollada en Java utilizando Spring Boot. Su objetivo es gestionar productos de la despensa y contribuir a la reducción del desperdicio de alimentos.

## Estructura del Proyecto
El proyecto está organizado de la siguiente manera:

```
Despensa Inteligente
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── despensa
│   │   │           └── inteligente
│   │   │               ├── controllers
│   │   │               │   └── ProductoController.java
│   │   │               ├── models
│   │   │               │   └── Producto.java
│   │   │               ├── repositories
│   │   │               │   └── ProductoRepository.java
│   │   │               ├── services
│   │   │               │   └── ProductoService.java
│   │   │               └── DespensaInteligenteApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       └── java
│           └── com
│               └── despensa
│                   └── inteligente
│                       └── DespensaInteligenteApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Requisitos
- Implementación de un CRUD funcional (crear, leer, actualizar, eliminar) para la gestión de productos.
- Conexión a una base de datos MySQL.
- Cobertura de pruebas del 65%.
- Manejo de errores básico utilizando ExceptionHandler.

## Instrucciones de Configuración
1. Clona el repositorio en tu máquina local.
2. Asegúrate de tener Java y Maven instalados.
3. Configura la base de datos MySQL y actualiza el archivo `application.properties` con tus credenciales.
4. Ejecuta la aplicación utilizando el comando `./mvnw spring-boot:run` en la terminal.

## Uso
Puedes probar la funcionalidad del CRUD utilizando herramientas como Postman. Asegúrate de que los endpoints RESTful estén funcionando correctamente.

## Contribuciones
Si deseas contribuir al proyecto, por favor abre un issue o envía un pull request.

## Licencia
Este proyecto está bajo la Licencia MIT.