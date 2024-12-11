# Problemas y Soluciones

## Problema 1: Conexión a la base de datos MySQL
### Descripción:
Al intentar conectar la aplicación a la base de datos MySQL, se produjo un error de conexión.

### Solución:
1. Verificar que el servidor MySQL esté en funcionamiento.
2. Asegurarse de que las credenciales de la base de datos en `application.properties` sean correctas.
3. Añadir el controlador MySQL en el archivo `pom.xml`:
    ```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
    </dependency>
    ```

## Problema 2: Manejo de errores en los endpoints
### Descripción:
Los errores no se manejaban adecuadamente, lo que resultaba en respuestas no informativas para el cliente.

### Solución:
1. Crear una clase global para manejar excepciones usando `@ControllerAdvice` y `@ExceptionHandler`.
    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ```

## Problema 3: Cobertura de pruebas insuficiente
### Descripción:
La cobertura de pruebas era inferior al 65% requerido.

### Solución:
1. Escribir pruebas unitarias adicionales para los servicios y controladores.
2. Utilizar herramientas como JaCoCo para medir la cobertura de pruebas.
    ```xml
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.7</version>
        <executions>
            <execution>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
            </execution>
            <execution>
                <id>report</id>
                <phase>test</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```

## Problema 4: Organización del proyecto
### Descripción:
La estructura del proyecto no estaba clara, lo que dificultaba el desarrollo y mantenimiento.

### Solución:
1. Seguir una estructura de carpetas organizada y coherente.
2. Documentar la estructura del proyecto en el archivo `README.md`.

## Problema 5: Configuración del repositorio
### Descripción:
El repositorio no estaba configurado correctamente, lo que causaba problemas de integración continua.

### Solución:
1. Configurar el archivo `.gitignore` para excluir archivos innecesarios.
2. Añadir un archivo `README.md` con instrucciones claras para configurar y ejecutar el proyecto.
3. Configurar un pipeline de CI/CD utilizando GitHub Actions o Jenkins.