# Proyecto de Tests Automatizados con Playwright y Java
Este proyecto contiene una suite de tests de UI automatizados utilizando un framework profesional basado en Playwright, Java, Maven y Docker. Incluye un sistema de reportes interactivos con Allure.

## Prerrequisitos
Para poder ejecutar este proyecto, solo necesitas tener instalado lo siguiente en tu máquina (preferiblemente Ubuntu o un sistema Linux similar):

1. Git: Para clonar el repositorio.

2. Docker Engine: Para construir y ejecutar el entorno de pruebas aislado.

Nota: Asegúrate de que tu usuario pertenece al grupo docker para no necesitar sudo. Si no es así, ejecútalo una vez:

```bash
sudo usermod -aG docker ${USER}
```

... y después cierra sesión y vuelve a iniciarla.

3. xdg-utils (Opcional): Para que los reportes de Allure se abran automáticamente en el navegador.

```bash
sudo apt install -y xdg-utils
```
No es necesario tener Java o Maven instalados localmente.

## Guía de Uso
### Configuración Inicial (Solo la primera vez)
Este proceso de un solo paso prepara tu entorno local, generando el archivo state.json necesario para gestionar el banner de cookies del sitio web.

1. Clona el repositorio:

```bash
git clone https://github.com/maalvrei/elDiarioTests.git
```
```bash
cd elDiarioTests
```
2. Construye la imagen de Docker:

```bash
docker build -t eldiario-tests .
```
3. Ejecuta el script de Setup para crear state.json:
Este comando ejecutará la clase Setup dentro de un contenedor para crear el archivo state.json en tu máquina.

```bash
docker run --rm -v "$(pwd):/app" eldiario-tests mvn compile exec:java -Dexec.mainClass="com.elDiarioTest.setup.Setup"
```
Al terminar, verás un nuevo archivo state.json en la carpeta de tu proyecto.

### Ejecución de los Tests (Uso diario)
Una vez completada la configuración inicial, para ejecutar la suite de tests completa, sigue estos pasos.

1.  (Opcional) Reconstruye la imagen si has cambiado el código:
Si has modificado algún archivo .java, el pom.xml o el Dockerfile, necesitarás reconstruir la imagen.

```bash
docker build -t eldiario-tests .
```
2. Limpia y ejecuta los tests:

    2.1 Limpia ejecuciones anteriores
```bash
mvn clean
```

    2.2. Ejecuta los tests dentro del contenedor
    
```bash
docker run --rm \
  -v "$(pwd)/target:/app/target" \
  -v "$(pwd)/screenshots:/app/screenshots" \
  eldiario-tests
```
3. Visualización de Reportes
El contenedor de Docker crea los archivos de resultados, pero el propietario de estos es root. Para poder visualizarlos, primero debes reclamar la propiedad de la carpeta target.

    3.1. Corrige los permisos de la carpeta de resultados:

```bash
sudo chown -R $USER:$USER target
```

    3.2. Lanza el reporte de Allure:
    
```bash
mvn allure:serve
```

Esto abrirá un reporte HTML interactivo en tu navegador web. Para detener el servidor del reporte, vuelve a la terminal y pulsa Ctrl + C.








