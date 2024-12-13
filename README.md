# ✨ **Despensa Inteligente** ✨

## 📄 **Descripción del Proyecto**

**Despensa Inteligente** es una aplicación backend desarrollada en **Java** con **Spring Boot** que permite realizar un CRUD para gestionar productos en una despensa.  
Aunque es una implementación inicial, refleja el esfuerzo dedicado al aprendizaje y desarrollo.

---

## 🛠️ **Trabajo Realizado**

- **CRUD Funcional**: Implementación completa para la gestión de productos.
- **Conexión con MySQL**: Configuración del proyecto para utilizar una base de datos relacional.
- **Pruebas Básicas**: Validación de las funcionalidades principales mediante pruebas automatizadas.
=======
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
DI-1-feature/mysql-configuration

---

## 💡 **Aprendizaje**

- **Organización y Objetividad**: Aprendí la importancia de planificar mejor y establecer metas claras desde el inicio.
- **Desarrollo Autónomo**: Este proyecto fue un desafío personal que me permitió aprender a resolver problemas por mi cuenta.
- **Trabajo en Equipo**: Gracias al apoyo de mis compañeras **Lara**, **Nau**, **Pilar**, **Ale** y **Lupe**, logré completar la presentación del proyecto, incluso en momentos donde sentí que no podía continuar.

---

## ❤️ **Agradecimientos**

Este proyecto es el resultado de mucho esfuerzo y dedicación, pero también de la colaboración y motivación de mis compañeras:  
**Lara**, **Nau**, **Pilar**, **Ale** y **Lupe**.  

Sin su ayuda, habría cerrado todo y abandonado. Gracias a ellas, hoy puedo presentar este trabajo con orgullo.

---

## 📝 **Reflexión Final**

Este proyecto no solo me ayudó a avanzar técnicamente, sino también a reconocer áreas de mejora personal, como la organización y la gestión del tiempo.  
Me llevo un aprendizaje valioso para ser más objetiva y eficiente en el futuro.

---

## 📚 **Normalización**
![Normalizacion](https://github.com/user-attachments/assets/c5ef49d5-233b-4837-8102-b2308b3b39bd)

El diseño de la base de datos sigue los principios de normalización para garantizar eficiencia y consistencia:

1. **Primera Forma Normal (1NF)**:  
   - Todos los datos son atómicos y no contienen listas o conjuntos.

2. **Segunda Forma Normal (2NF)**:  
   - Todas las columnas dependen completamente de la clave primaria, eliminando dependencias parciales.

3. **Tercera Forma Normal (3NF)**:  
   - No hay dependencias transitivas; cada columna es dependiente exclusivamente de la clave primaria.

---

🌐 Diagrama UML del Proyecto
El siguiente diagrama UML representa la estructura de las entidades y sus relaciones:


---![UML](https://github.com/user-attachments/assets/24ffbdb0-9764-46e2-8ffb-cfd1a9fcfbd1)


