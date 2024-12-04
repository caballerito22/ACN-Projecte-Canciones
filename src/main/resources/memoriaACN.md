# Memoria del Proyecto: Generador de Pàgines Web Estàtiques - Cantantes con Canciones -

## Portada

**Título:** Generador de Pàgines Web Estàtiques sobre Cantantes con sus canciones  
**Autor:** Alejandro Caballero Núñez  
**Fecha:** 03 de noviembre de 2024

---

## Índice
1. [Introcucción](#1-introcucción)
2. [Descripción del Proyecto](#2-descripción del proyecto)
3. [Ficheros de Entrada](#3-ficheros de entrada)
    - [Archivo JSON](#31-archivo-json)
    - [Archivo JSON Schema](#32-archivo-json-schema)
    - [Archivo INI](#33-archivo-ini)
4. [Descripción de Librerias, Clases y Dependencias](#4-descripción-de-librerias-clases-y-dependencias)
5. [Plantillas Thymeleaf](#5-plantillas-thymeleaf)
6. [Fcheros de Salida](#6-ficheos-de-salida)
7. [Problemas Resueltos y No Resueltos](#7-problemas-resueltos-y-no-resueltos)
8. [Webgrafia](#8-webgrafia)

---

## 1. Introcucción
En este proyecto hemos creado un generador automático de páginas web estáticas. De esta manera hemos presentado información sobre cantantes y sus canciones más famosas. Todo esto mediante archivos de entrada incluyendo datos en formato JSON que se validan con un esquema JSON Schema. Luego, estos datos se usan para generar páginas HTML con plantillas Thymeleaf.

---

## 2. Descripción del Proyecto
El sistema lee los archivos de entrada proporcionados (JSON, INI) y genera páginas web personalizadas. Estas páginas muestran detalles sobre cantantes y sus canciones. Los pasos principales son:

1. Validación del archivo JSON con JSON Schema.
2. Lectura de configuraciones desde el archivo INI.
3. Generación de páginas HTML utilizando Thymeleaf.
4. Creación de un archivo RSS con las novedades del proyecto.

---

## 3. Archivos de Entrada

### 3.1 Archivo JSON
Este archivo contiene la información principal de los cantantes y canciones. Ejemplo:
```json
{
  "cantantes": [
    {
      "nombre": "Myke Towers",
      "añoNacimiento": 1994,
      "pais": "Puerto Rico",
      "imagen": "https://...",
      "canciones": [
        {
          "título": "La Jeepeta",
          "añoLanzamiento": 2020,
          "reproducciones": 5000000,
          "imagen": "https://..."
        }
      ]
    }
  ]
}
```

### 3.2 Archivo JSON Schema
Este esquema valida la estructura del archivo JSON:
```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "cantantes": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "nombre": { "type": "string" },
          "añoNacimiento": { "type": "integer" },
          "pais": { "type": "string" },
          "imagen": { "type": "string" },
          "canciones": {
            "type": "array",
            "items": {
              "type": "object",
              "properties": {
                "título": { "type": "string" },
                "añoLanzamiento": { "type": "integer" },
                "reproducciones": { "type": "integer" },
                "imagen": { "type": "string" }
              }
            }
          }
        }
      }
    }
  }
}
```

### 3.3 Archivo INI
Este archivo contiene la configuración del proyecto:
```ini
[nombre]
nombre = Mejores Cantantes y Canciones
[descripción]
descripción = Una colección de artistas y sus canciones destacadas
```

---

## 4. Descripción de Librerías, Clases y Dependencias

- **Librerías Utilizadas:**
    - `Jackson`: Para la deserialización y validación de archivos JSON.
    - `Thymeleaf`: Para la generación de páginas HTML.

- **Dependencias Maven:**
```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.15.0</version>
</dependency>

```

- **Clases Java:**
    - `Cantante`: Modelo para representar un cantante.
    - `Canción`: Modelo para representar una canción.
    - `GeneradorPaginas`: Clase principal para gestionar la generación de páginas.

---

## 5. Plantillas Thymeleaf
- **Plantilla Principal (`index.html`):** Lista todos los cantantes.
- **Plantilla de Detalles (`detalles.html`):** Muestra información sobre un cantante y sus mejores canciones.

Ejemplo de código Thymeleaf:
```html
<!DOCTYPE html>
<html>
<head>
    <title>Mejores Cantantes</title>
</head>
<body>
<h1 th:text="${config.nombre}"></h1>
<ul>
    <li th:each="cantante : ${cantantes}">
        <a th:href="@{'/cantante/' + ${cantante.nombre}}" th:text="${cantante.nombre}"></a>
    </li>
</ul>
</body>
</html>
```

---

## 6. Archivos de Salida
Los archivos HTML se generan en la carpeta `output/`:
- `index.html`: Lista principal de cantantes.
- `detalles.html`: Una página para cada cantante.

### Captura de ejemplo
![Captura del sitio web](https://via.placeholder.com/800x400 "Captura del sitio web")

---

## 7. Problemas Resueltos y No Resueltos
- **Resueltos:**
    - Validación del JSON con esquemas.
    - Integración de Thymeleaf con el proyecto.
- **No Resueltos:**
    - Automatización de pruebas unitarias para la validación.
    - Errores ocasionales en el RSS por caracteres especiales.

---

## 8. Webgrafía
1. **Documentación JSON Schema:** [https://json-schema.org](https://json-schema.org)
2. **Thymeleaf Tutorials:** [https://www.thymeleaf.org](https://www.thymeleaf.org)
3. **Jackson Documentation:** [https://github.com/FasterXML/jackson](https://github.com/FasterXML/jackson)
4. **Referencias RSS:** [https://www.rssboard.org/rss-specification](https://www.rssboard.org/rss-specification)

