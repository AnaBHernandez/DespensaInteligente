#!/bin/bash

echo "🚀 Iniciando Despensa Inteligente..."
echo "📋 Verificando dependencias..."

# Verificar si Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven no está instalado. Instalando..."
    sudo apt update
    sudo apt install -y maven
fi

# Verificar si Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java no está instalado. Instalando..."
    sudo apt update
    sudo apt install -y openjdk-21-jdk
fi

echo "✅ Dependencias verificadas"
echo "🔧 Compilando proyecto..."

# Compilar el proyecto
mvn clean compile

echo "🚀 Ejecutando aplicación..."
echo "📱 La aplicación estará disponible en: http://localhost:8080"
echo "📋 Endpoints disponibles:"
echo "   - GET /productos - Obtener todos los productos"
echo "   - POST /productos - Crear producto"
echo "   - POST /productos/escanear-completo - Escaneo completo"
echo "   - GET /productos/alertas - Productos próximos a caducar"
echo ""
echo "🛑 Para detener la aplicación, presiona Ctrl+C"

# Ejecutar la aplicación
mvn spring-boot:run
