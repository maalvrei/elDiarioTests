# Proyecto de Tests Automatizados con Playwright y Java

Suite de pruebas de UI automatizadas utilizando un framework profesional basado en **Playwright**, **Java**, **Maven** y **Docker**.  
Incluye reportes interactivos con **Allure**.

---

## 📦 Prerrequisitos

Para ejecutar este proyecto necesitas tener instalado en tu máquina (preferiblemente Ubuntu o un sistema Linux similar):

1. **Git** – Para clonar el repositorio.
2. **Docker Engine** – Para construir y ejecutar el entorno de pruebas aislado.  
   Asegúrate de que tu usuario pertenece al grupo `docker` para no necesitar `sudo`:
   ```bash
   sudo usermod -aG docker ${USER}
   ```
   Cierra sesión y vuelve a iniciarla después.
3. **xdg-utils** *(opcional)* – Para que los reportes de Allure se abran automáticamente:
   ```bash
   sudo apt install -y xdg-utils
   ```

> 💡 **Nota:** No es necesario tener Java o Maven instalados localmente.

---

## 🚀 Guía de Uso

### 1️⃣ Configuración Inicial (solo la primera vez)

Este proceso crea el archivo `state.json` necesario para gestionar el banner de cookies del sitio web.

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/maalvrei/elDiarioTests.git
   cd elDiarioTests
   ```

2. Construir la imagen de Docker:

   ```bash
   docker build -t eldiario-tests .
   ```

---

### 2️⃣ Ejecución de los Tests (uso diario)

#### (Opcional) Reconstruir la imagen si hay cambios en el código:

```bash
docker build -t eldiario-tests .
```

#### 2.1 Limpiar ejecuciones anteriores:

```bash
mvn clean
```

#### 2.2 Ejecutar los tests dentro del contenedor:

```bash
docker run --rm   -v "$(pwd)/target:/app/target"   -v "$(pwd)/screenshots:/app/screenshots"   eldiario-tests
```

---

### 3️⃣ Visualización de Reportes

Los resultados generados por Docker tienen como propietario al usuario `root`.  
Para poder abrirlos con Allure, primero debes cambiar los permisos:

#### 3.1 Corregir permisos:

```bash
sudo chown -R $USER:$USER target
```

#### 3.2 Abrir el reporte Allure:

```bash
mvn allure:serve
```

Esto abrirá un reporte HTML interactivo en tu navegador.  
Para detener el servidor, vuelve a la terminal y pulsa `Ctrl + C`.

---

## 🗂 Estructura del Proyecto

```
elDiarioTests/
├── pom.xml               # Configuración Maven
├── Dockerfile            # Imagen Docker del entorno de tests
├── src/                  # Código fuente de los tests
├── target/               # Resultados y artefactos de ejecución
└── screenshots/          # Capturas de pantalla de fallos
```

---

## 📜 Licencia

Este proyecto se distribuye bajo la licencia MIT.
