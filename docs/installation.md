# 🚀 Guía de Instalación - Despensa Inteligente

## 📋 Requisitos Previos

### **Software Necesario**
- **Java 21** - [Descargar OpenJDK 21](https://openjdk.java.net/projects/jdk/21/)
- **Maven 3.8+** - [Descargar Maven](https://maven.apache.org/download.cgi)
- **Git** - [Descargar Git](https://git-scm.com/downloads)

### **Verificar Instalación**
```bash
# Verificar Java
java -version
# Debe mostrar: openjdk version "21.x.x"

# Verificar Maven
mvn -version
# Debe mostrar: Apache Maven 3.8.x

# Verificar Git
git --version
```

## 🔧 Instalación Paso a Paso

### **1. Clonar el Repositorio**
```bash
git clone https://github.com/TU-USUARIO/DespensaInteligente.git
cd DespensaInteligente
```

### **2. Compilar el Proyecto**
```bash
# Limpiar y compilar
mvn clean compile

# Verificar que no hay errores
mvn test
```

### **3. Ejecutar la Aplicación**

#### **Opción A: Usando Maven**
```bash
mvn spring-boot:run
```

#### **Opción B: Usando el Script**
```bash
# Dar permisos de ejecución
chmod +x ejecutar.sh

# Ejecutar
./ejecutar.sh
```

#### **Opción C: Usando JAR**
```bash
# Compilar JAR
mvn clean package

# Ejecutar JAR
java -jar target/despensa-inteligente-1.0-SNAPSHOT.jar
```

## 🌐 Acceso a la Aplicación

Una vez ejecutada, la aplicación estará disponible en:

- **API Principal**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
- **Documentación**: http://localhost:8080 (página de inicio)

### **Credenciales H2 Console**
- **JDBC URL**: `jdbc:h2:mem:despensa_inteligente`
- **User Name**: `sa`
- **Password**: (dejar vacío)

## 🔧 Configuración Avanzada

### **Base de Datos MySQL (Producción)**
```properties
# En src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/despensa_inteligente
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

### **Configuración de Archivos**
```properties
# Tamaño máximo de archivos para OCR
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

## 🐛 Solución de Problemas

### **Error: Java no encontrado**
```bash
# Instalar Java 21 en Ubuntu/Debian
sudo apt update
sudo apt install openjdk-21-jdk

# Verificar instalación
java -version
```

### **Error: Maven no encontrado**
```bash
# Instalar Maven en Ubuntu/Debian
sudo apt update
sudo apt install maven

# Verificar instalación
mvn -version
```

### **Error: Puerto 8080 ocupado**
```bash
# Cambiar puerto en application.properties
server.port=8081
```

### **Error: Tesseract OCR**
```bash
# Instalar Tesseract en Ubuntu/Debian
sudo apt update
sudo apt install tesseract-ocr tesseract-ocr-spa

# Verificar instalación
tesseract --version
```

## ✅ Verificación de Instalación

### **Test de Endpoints**
```bash
# Verificar que la aplicación responde
curl http://localhost:8080/productos

# Debe devolver: [] (lista vacía inicialmente)
```

### **Test de Base de Datos**
1. Ir a http://localhost:8080/h2-console
2. Conectar con las credenciales mencionadas
3. Verificar que existe la tabla `PRODUCTO`

## 🚀 Próximos Pasos

1. **Probar la API** - Ver [API Reference](api-reference.md)
2. **Entender la arquitectura** - Ver [Architecture](architecture.md)
3. **Resolver problemas** - Ver [Troubleshooting](troubleshooting.md)

---

**¡Instalación completada exitosamente!** 🎉
