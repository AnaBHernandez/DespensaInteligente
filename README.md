# Despensa Inteligente

## Descripción del Proyecto
Despensa Inteligente es una aplicación backend desarrollada en Java utilizando Spring Boot. Su objetivo es gestionar productos de la despensa y contribuir a la reducción del desperdicio de alimentos.

## Requisitos
- Implementación de un CRUD funcional (crear, leer, actualizar, eliminar) para la gestión de productos.
- Conexión a una base de datos MySQL.
- Cobertura de pruebas del 65%.
- Manejo de errores básico utilizando ExceptionHandler.

## Instrucciones de Configuración
1. Clona el repositorio en tu máquina local:
    ```sh
    git clone https://github.com/AnaBHernandez/DespensaInteligente.git
    ```
2. Asegúrate de tener Java y Maven instalados.
3. Configura la base de datos MySQL y actualiza el archivo `application.properties` con tus credenciales.
4. Ejecuta la aplicación utilizando el comando:
    ```sh
    ./mvnw spring-boot:run
    ```

## Uso
Puedes probar la funcionalidad del CRUD utilizando herramientas como Postman. Asegúrate de que los endpoints RESTful estén funcionando correctamente.

## Contribuciones
Este proyecto está siendo desarrollado por mí, Ana Belen Hernández. Si tienes sugerencias o encuentras algún problema, por favor abre un issue o envía un pull request.

## Licencia
Este proyecto está bajo la Licencia MIT. Desarrollado por Ana Belen Hernández.

# Documentación de Problemas y Soluciones

## Problema 1: Conexión a la base de datos MySQL

### Descripción:
Al intentar conectar la aplicación a la base de datos MySQL, se produjo un error de conexión.

### Solución:
1. Verificar que el servidor MySQL esté en funcionamiento.
2. Asegurarse de que las credenciales de la base de datos en `application.properties` sean correctas.
3. Añadir el controlador MySQL en el archivo `pom.xml`.

## Problema 2: Manejo de errores en los endpoints

### Descripción:
[Descripción del problema]

### Solución:
[Solución aplicada]

# Configuración del Proyecto y Subida a GitHub

## 1. Inicialización de Git

1. Inicializamos un repositorio Git en el proyecto:
    ```sh
    git init
    ```

## 2. Creación de Archivos Esenciales

1. Creamos y configuramos el archivo `README.md` con la descripción del proyecto, estructura, requisitos, instrucciones de configuración, uso, contribuciones y licencia.
2. Creamos y configuramos el archivo `.gitignore` para excluir archivos y carpetas innecesarios del control de versiones.

## 3. Realización del Primer Commit

1. Añadimos todos los archivos al área de preparación:
    ```sh
    git add .
    ```

2. Realizamos el primer commit con un mensaje detallado:
    ```sh
    git commit -m "Initial commit: Setup Despensa Inteligente project with basic structure and essential files"
    ```

## 4. Creación de Ramas `main` y `dev`

1. Creamos la rama `main` a partir de `master`:
    ```sh
    git checkout -b main
    ```

2. Volvimos a la rama `master`:
    ```sh
    git checkout master
    ```

3. Creamos la rama `dev` a partir de `master`:
    ```sh
    git checkout -b dev
    ```

## 5. Conexión al Repositorio Remoto en GitHub

1. Añadimos el repositorio remoto:
    ```sh
    git remote add origin https://github.com/AnaBHernandez/DespensaInteligente.git
    ```

## 6. Subida de Ramas al Repositorio Remoto

1. Empujamos la rama `main` al repositorio remoto:
    ```sh
    git push -u origin main
    ```

2. Aseguramos estar en la rama `dev`:
    ```sh
    git checkout dev
    ```

3. Empujamos la rama `dev` al repositorio remoto:
    ```sh
    git push -u origin dev
    ```

## Resumen de Comandos Utilizados

```sh
# Inicialización de Git
git init

# Añadir archivos al área de preparación y realizar el primer commit
git add .
git commit -m "Initial commit: Setup Despensa Inteligente project with basic structure and essential files"

# Creación de ramas
git checkout -b main
git checkout master
git checkout -b dev

# Conexión al repositorio remoto
git remote add origin https://github.com/AnaBHernandez/DespensaInteligente.git

# Subida de ramas al repositorio remoto
git push -u origin main
git checkout dev
git push -u origin dev