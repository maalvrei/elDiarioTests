# --- ETAPA 1: Construcción y Ejecución de Tests ---

# Usamos la imagen oficial de Microsoft para Playwright con Java.
# ¡Es la mejor opción! Ya incluye Java, Maven y todas las librerías
# del sistema que necesitan los navegadores para funcionar.
FROM mcr.microsoft.com/playwright/java:v1.45.0-jammy

# Establecemos el directorio de trabajo dentro del contenedor.
# Todos los comandos siguientes se ejecutarán desde esta carpeta.
WORKDIR /app

# Copiamos solo el archivo de dependencias primero.
# Esto es un truco de caché de Docker: si no cambias tus dependencias,
# Docker no las volverá a descargar cada vez que cambies tu código.
COPY pom.xml .

# Ejecutamos un comando de Maven para descargar todas las dependencias
# y tenerlas listas.
RUN mvn dependency:go-offline

# Ahora, copiamos el resto del código fuente de nuestro proyecto
# (todo lo que está en la carpeta 'src').
COPY src ./src
COPY state.json .

# Creamos un directorio para guardar los resultados de los tests (screenshots).
# Este directorio estará dentro del contenedor.
RUN mkdir screenshots

# Este es el comando que se ejecutará por defecto cuando inicies el contenedor.
# Simplemente le dice a Maven que ejecute los tests.
CMD ["mvn", "test"]