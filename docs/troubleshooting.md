# 🔧 Solución de Problemas - Despensa Inteligente

## 🚨 Problemas Comunes y Soluciones

### **Problema 1: Incompatibilidad de Spring Boot 2.5.4 con Java 21**
**Síntomas:**
- Error de compilación: "Unsupported class file major version"
- Maven falla al compilar

**Solución:**
Actualizar Spring Boot a la versión 3.2.0 en el `pom.xml`:
```xml
<properties>
    <java.version>21</java.version>
    <spring.boot.version>3.2.0</spring.boot.version>
</properties>
```

### **Problema 2: Migración de javax.persistence a jakarta.persistence**
**Síntomas:**
- Error: "package javax.persistence does not exist"
- Imports fallan

**Solución:**
Cambiar todas las importaciones de `javax.persistence` a `jakarta.persistence`:
```java
// Antes (Spring Boot 2.x)
import javax.persistence.*;

// Después (Spring Boot 3.x)
import jakarta.persistence.*;
```

### **Problema 3: Configuración del compilador Maven**
**Síntomas:**
- Error: "Source option 5 is no longer supported"
- Maven usa versión antigua del compilador

**Solución:**
Configurar el plugin de compilación en `pom.xml`:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
        <source>21</source>
        <target>21</target>
    </configuration>
</plugin>
```

### **Problema 4: Base de datos MySQL vs H2**
**Síntomas:**
- Error de conexión a MySQL
- Configuración compleja para desarrollo

**Solución:**
Usar H2 (base de datos en memoria) para desarrollo:
```properties
# En application.properties
spring.datasource.url=jdbc:h2:mem:despensa_inteligente
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```

### **Problema 5: Dependencias faltantes para OCR**
**Síntomas:**
- Error: "ClassNotFoundException: net.sourceforge.tess4j"
- OCR no funciona

**Solución:**
Agregar dependencias en `pom.xml`:
```xml
<!-- Tesseract OCR (GRATUITO) -->
<dependency>
    <groupId>net.sourceforge.tess4j</groupId>
    <artifactId>tess4j</artifactId>
    <version>5.8.0</version>
</dependency>

<!-- Para manejo de archivos e imágenes -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

### **Problema 6: Configuración de archivos para upload**
**Síntomas:**
- Error: "Maximum upload size exceeded"
- No se pueden subir imágenes

**Solución:**
Configurar en `application.properties`:
```properties
# Configuración para archivos
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### **Problema 7: Puerto 8080 ocupado**
**Síntomas:**
- Error: "Port 8080 was already in use"
- Aplicación no inicia

**Solución:**
```bash
# Opción 1: Cambiar puerto
echo "server.port=8081" >> src/main/resources/application.properties

# Opción 2: Matar proceso en puerto 8080
sudo lsof -ti:8080 | xargs kill -9

# Opción 3: Usar puerto diferente
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### **Problema 8: Tesseract OCR no funciona**
**Síntomas:**
- Error: "Tesseract not found"
- OCR falla al procesar imágenes

**Solución:**
```bash
# Instalar Tesseract en Ubuntu/Debian
sudo apt update
sudo apt install tesseract-ocr tesseract-ocr-spa
# Instalar el idioma español para Tesseract
# Verificar instalación
tesseract --version

# En Windows, descargar desde:
# https://github.com/UB-Mannheim/tesseract/wiki
```

### **Problema 9: Java no encontrado**
**Síntomas:**
- Error: "java: command not found"
- Maven falla

**Solución:**
```bash
# Instalar Java 21 en Ubuntu/Debian
sudo apt update
sudo apt install openjdk-21-jdk

# Verificar instalación
java -version

# Configurar JAVA_HOME si es necesario
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

### **Problema 10: Maven no encontrado**
**Síntomas:**
- Error: "mvn: command not found"
- No se puede compilar

**Solución:**
```bash
# Instalar Maven en Ubuntu/Debian
sudo apt update
sudo apt install maven

# Verificar instalación
mvn -version
```

## 🔍 Diagnóstico de Problemas

### **Verificar Estado de la Aplicación**
```bash
# Verificar que la aplicación está corriendo
curl -I http://localhost:8080/productos

# Verificar logs
tail -f logs/application.log
```

### **Verificar Base de Datos H2**
1. Ir a http://localhost:8080/h2-console
2. Conectar con:
   - JDBC URL: `jdbc:h2:mem:despensa_inteligente`
   - User Name: `sa`
   - Password: (vacío)

### **Verificar Dependencias**
```bash
# Verificar que todas las dependencias están descargadas
mvn dependency:tree

# Limpiar y reinstalar dependencias
mvn clean install
```

### **Verificar Configuración**
```bash
# Verificar configuración de la aplicación
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"

# Verificar propiedades
curl http://localhost:8080/actuator/configprops
```

## 🛠️ Comandos de Diagnóstico

### **Logs Detallados**
```bash
# Ejecutar con logs detallados
mvn spring-boot:run -Dspring-boot.run.arguments="--logging.level.org.springframework.web=DEBUG"

# Ver logs en tiempo real
tail -f target/logs/spring.log
```

### **Test de Conectividad**
```bash
# Test básico
curl http://localhost:8080/productos

# Test con verbose
curl -v http://localhost:8080/productos

# Test de creación
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Test","precio":1.0,"cantidad":1}'
```

### **Verificar Recursos del Sistema**
```bash
# Verificar uso de memoria
free -h

# Verificar procesos Java
ps aux | grep java

# Verificar puertos en uso
netstat -tulpn | grep :8080
```

## 🚨 Errores Críticos

### **Error: OutOfMemoryError**
**Solución:**
```bash
# Aumentar memoria para Maven
export MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=256m"

# Aumentar memoria para Spring Boot
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx1024m"
```

### **Error: Database Connection Failed**
**Solución:**
```bash
# Verificar que H2 está configurado correctamente
grep -r "h2" src/main/resources/application.properties

# Reiniciar aplicación
mvn clean spring-boot:run
```

### **Error: File Upload Failed**
**Solución:**
```bash
# Verificar configuración de archivos
grep -r "multipart" src/main/resources/application.properties

# Verificar permisos de directorio temporal
ls -la /tmp
```

## 📞 Obtener Ayuda

### **Logs Útiles**
```bash
# Logs de Spring Boot
tail -f logs/spring.log

# Logs de Maven
mvn clean compile -X

# Logs del sistema
journalctl -u tu-servicio -f
```

### **Información del Sistema**
```bash
# Información de Java
java -version
echo $JAVA_HOME

# Información de Maven
mvn -version

# Información del sistema
uname -a
cat /etc/os-release
```

---

**¡Problemas resueltos!** 🎉
