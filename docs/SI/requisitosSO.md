# 🖥️ Requisitos del Sistema - RetroPVPArena

---

## Justificación del Entorno

El proyecto se ha diseñado para ejecutarse en un entorno de desarrollo local basado en contenedores.

* **Base de Datos:** Se ha elegido **Podman** para aislar la base de datos MySQL, permitiendo una portabilidad total entre
equipos de desarrollo.

* **Aplicación de Gestión:** esta desarrollada en **JavaFX**, lo que requiere
un entorno de ejecución con soporte grafico Nativo.

---

## 🛠️ Requisitos de Hardware

**Componente**              **Requisitos Mínimos**              **Especificaciones Recomendadas**

Procesador(CPU)             Dual-Core x86_64 con soporte        Quad-Core (i5/Ryzen 5) o superior
                            para Virtualización.                con soporte para VT.

Memoria RAM                 8GB (2GB al container               16GB (4GB al container
                            de la BBDD)                         podman/BBDD)

Almacenamiento              5GB libres (HDD)                    10GB libres (HDD)

Gráficos                    Integrada con soporte               Dedicada con 2GB o integrada moderna
                            OpenGL 2.0                          (Para JavaFX)

Red                         Conexiones para descarga            Conexión de banda ancha estable
                            de imagenes/librerias           


---

## 📂 Requisitos de Software

* **Sistema operativo:** windows 10/11 (home o pro), linux (Ubuntu 22.04 LTS o superior) o macOS.

* **Entorno de contenedores:** Podman Desktop 4.0+ o Podman CLI. Tambien sería valido XAMPP.

* **Java Runtime:** OpenJDK 17 o superior (incluyendo el módulo de javaFX).

* **Navegador Web:** Google Chrome, Firefox, Edge, Opera. Tambien valido cualquier navegador chromium.

* **Gestor de BBDD:** DBeaver community edition o PHPmyadmin.


> **Nota de Seguridad:** Se recomienda encarecidamente el uso de **Podman** frente a XAMPP para asegurar la integridad de los datos y la paridad entre entornos de desarrollo y producción.