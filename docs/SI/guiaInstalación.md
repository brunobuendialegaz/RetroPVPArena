Esta guía explica paso a paso cómo instalar el proyecto desde cero en los tres sistemas operativos principales.

## 1. Clonación del Proyecto
Independientemente del sistema operativo, el primer paso es descargar el código fuente:

```bash
git clone https://github.com/brunobuendialegaz/RetroPVPArena.git \
cd RetroPVPArena
```

## 2. Instalación por Sistema Operativo

🐧 Linux (Ubuntu/Debian)
Actualizar el sistema: 

```bash
sudo apt update
```

Instalar dependencias:

```Bash
sudo apt install git podman maven openjdk-21-jdk -y
```
Verificar: 
```Bash
java -version \
podman --version.
```

(Alternativamente puedes usar Docker o Xampp)

🪟 Windows 10/11
Instalar Podman Desktop (Alternativamente puedes usar Docker o Xampp): Descargar desde podman-desktop.io (Asegúrate de habilitar WSL2).

Instalar JDK 21 y Maven: Se recomienda usar el instalador de Adoptium y el instalador oficial de Apache Maven.

Configurar Variables: Asegúrate de que JAVA_HOME y MAVEN_HOME están en el PATH del sistema.

🍎 macOS
Instalar Homebrew: (si no lo tienes) /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

Instalar dependencias:

```Bash
brew install git podman maven openjdk@21
```
Configurar Podman (Alternativamente puedes usar Docker o Xampp):

```Bash
podman machine init \
podman machine start
```


## 3. Despliegue de la Base de Datos
En cualquier sistema, con Podman instalado, ejecuta:

```Bash
podman run -d \
  --pod new:retro-pod \
  -p 3306:3306 \ -- Puedes seleccionar otro puerto si lo tienes ocupado
  --name mariadb-retro \
  -e MARIADB_ROOT_PASSWORD=root \
  -e MARIADB_DATABASE=retropvp_db \
  -v retropvp_vol:/var/lib/mysql \
  mariadb:latest
  ```

## 4. Inicialización del Esquema SQL
Abre DBeaver.

Conecta a localhost:3306 (O el puesto que hayas seleccionado para el despliegue del pod) con el usuario root:root.

Localiza el archivo schema.sql de creación de tablas en el repositorio clonado (bbdd/retropvp-schema.sql) y ejecútalo sobre la base de datos retropvp_db, despues ejecuta el archivo (bbdd/retropvp-init.sql).

## 5. Ejecución de la Aplicación
Desde la raíz del proyecto (donde está el pom.xml):


# Instalar dependencias de Maven y compilar
```Bash
mvn clean install
```

# Ejecutar la aplicación JavaFX
```Bash
mvn javafx:run
```