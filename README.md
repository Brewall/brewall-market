# Brewall Market API

API REST para la gestión de un sistema de supermercado desarrollada con Spring Boot.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Arquitectura](#arquitectura)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [API Endpoints](#api-endpoints)
- [Modelo de Datos](#modelo-de-datos)
- [Documentación API](#documentación-api)
- [Estructura del Proyecto](#estructura-del-proyecto)

## Descripción

Brewall Market es una API REST que proporciona funcionalidades para la gestión de productos, categorías, compras y clientes de un supermercado. El proyecto implementa una arquitectura por capas siguiendo principios de Domain-Driven Design (DDD).

## Tecnologías

- **Java:** 21
- **Spring Boot:** 3.5.6
- **Spring Data JPA:** Gestión de persistencia
- **PostgreSQL:** Base de datos relacional
- **MapStruct:** 1.6.3 - Mapeo de objetos
- **SpringDoc OpenAPI:** 2.1.0 - Documentación Swagger
- **Gradle:** Gestión de dependencias y construcción

## Arquitectura

El proyecto sigue una arquitectura hexagonal (Ports & Adapters) con tres capas principales:

### Capa Web (Presentación)
- Controladores REST
- Manejo de peticiones HTTP
- Documentación Swagger

### Capa de Dominio (Negocio)
- Modelos de dominio
- Interfaces de repositorio
- Servicios de lógica de negocio

### Capa de Persistencia (Datos)
- Entidades JPA
- Repositorios CRUD
- Mappers MapStruct

## Requisitos Previos

- Java JDK 21 o superior
- PostgreSQL 12 o superior
- Gradle (incluido wrapper)
- IDE con soporte Java (IntelliJ IDEA, Eclipse, VS Code)

## Instalación

1. Clonar el repositorio:
```bash
git clone <repository-url>
cd brewall-market
```

2. Crear la base de datos en PostgreSQL:
```sql
CREATE DATABASE "brewall-market";
```

3. Configurar las credenciales de base de datos en `src/main/resources/application-dev.properties`

## Configuración

### application.properties

Configuración principal de la aplicación:

```properties
spring.application.name=brewall-market
spring.profiles.active=dev
server.servlet.context-path=/brewall-market/api
springdoc.swagger-ui.path=/swagger-ui.html
```

### application-dev.properties

Configuración para entorno de desarrollo:

```properties
server.port=8090
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/brewall-market
spring.datasource.username=postgres
spring.datasource.password=password
```

**Nota:** Modifique las credenciales según su configuración local.

## Ejecución

### Usando Gradle Wrapper

**Windows:**
```bash
gradlew.bat bootRun
```

**Linux/Mac:**
```bash
./gradlew bootRun
```

### Construir el proyecto

```bash
./gradlew build
```

### Ejecutar JAR

```bash
./gradlew bootJar
java -jar build/libs/brewall-market-1.0.jar
```

La aplicación estará disponible en: `http://localhost:8090/brewall-market/api`

## API Endpoints

### Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/products/all` | Obtener todos los productos |
| GET | `/products/{id}` | Obtener producto por ID |
| GET | `/products/category/{categoryId}` | Obtener productos por categoría |
| POST | `/products/save` | Crear o actualizar un producto |
| DELETE | `/products/delete/{id}` | Eliminar un producto |

### Compras

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/purchases/all` | Obtener todas las compras |
| GET | `/purchases/client/{idClient}` | Obtener compras por cliente |
| POST | `/purchases/save` | Crear una nueva compra |

## Modelo de Datos

### Entidades Principales

**Producto (productos)**
- id_producto (PK)
- nombre
- id_categoria (FK)
- codigo_barras
- precio_venta
- cantidad_stock
- estado

**Categoria (categorias)**
- id_categoria (PK)
- descripcion
- estado

**Compra (compras)**
- id_compra (PK)
- id_cliente (FK)
- fecha
- medio_pago
- comentario
- estado

**Cliente (clientes)**
- id (PK)
- nombre
- apellidos
- celular
- direccion
- correo_electronico

**ComprasProducto (compras_productos)**
- id_compra (PK, FK)
- id_producto (PK, FK)
- cantidad
- total
- estado

### Relaciones

- Categoria → Producto (1:N)
- Cliente → Compra (1:N)
- Compra → ComprasProducto (1:N)
- Producto → ComprasProducto (1:N)

## Documentación API

La documentación interactiva de la API está disponible a través de Swagger UI:

**URL:** `http://localhost:8090/brewall-market/api/swagger-ui.html`

Swagger proporciona:
- Descripción detallada de todos los endpoints
- Modelos de datos
- Posibilidad de probar los endpoints directamente

## Estructura del Proyecto

```
brewall-market/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/brewall/market/
│   │   │       ├── BrewallMarketApplication.java
│   │   │       ├── domain/
│   │   │       │   ├── Category.java
│   │   │       │   ├── Product.java
│   │   │       │   ├── Purchase.java
│   │   │       │   ├── PurchaseItem.java
│   │   │       │   ├── repository/
│   │   │       │   │   ├── ProductRepository.java
│   │   │       │   │   └── PurchaseRepository.java
│   │   │       │   └── service/
│   │   │       │       ├── ProductService.java
│   │   │       │       └── PurchaseService.java
│   │   │       ├── persistence/
│   │   │       │   ├── CompraRepository.java
│   │   │       │   ├── ProductoRepository.java
│   │   │       │   ├── crud/
│   │   │       │   │   ├── CompraCrudRepository.java
│   │   │       │   │   └── ProductoCrudRepository.java
│   │   │       │   ├── entity/
│   │   │       │   │   ├── Categoria.java
│   │   │       │   │   ├── Cliente.java
│   │   │       │   │   ├── Compra.java
│   │   │       │   │   ├── ComprasProducto.java
│   │   │       │   │   ├── ComprasProductoPK.java
│   │   │       │   │   └── Producto.java
│   │   │       │   └── mapper/
│   │   │       │       ├── CategoryMapper.java
│   │   │       │       ├── ProductMapper.java
│   │   │       │       ├── PurchaseItemMapper.java
│   │   │       │       └── PurchaseMapper.java
│   │   │       └── web/
│   │   │           └── controller/
│   │   │               ├── ProductController.java
│   │   │               └── PurchaseController.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-pdn.properties
│   └── test/
│       └── java/
│           └── com/brewall/market/
│               └── BrewallMarketApplicationTests.java
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## Características Principales

### Mapeo de Objetos con MapStruct

El proyecto utiliza MapStruct para convertir automáticamente entre:
- Entidades de persistencia (nomenclatura en español)
- Objetos de dominio (nomenclatura en inglés)

### Consultas Personalizadas

Los repositorios implementan consultas personalizadas usando Spring Data JPA Query Methods:

- `findByIdCategoriaOrderByNombreAsc`: Productos ordenados por nombre
- `findByCantidadStockLessThanAndEstado`: Productos con stock bajo

### Manejo de Respuestas

Los controladores utilizan `ResponseEntity` con códigos HTTP apropiados:
- 200 OK: Operación exitosa
- 201 CREATED: Recurso creado
- 404 NOT_FOUND: Recurso no encontrado

## Mejoras Futuras

- Implementar validaciones con Bean Validation (`@Valid`, `@NotNull`)
- Agregar manejo global de excepciones con `@ControllerAdvice`
- Implementar Spring Security para autenticación y autorización
- Agregar paginación en endpoints de listado
- Implementar tests unitarios e integración
- Externalizar configuración sensible a variables de entorno
- Agregar logging estructurado
- Implementar DTOs específicos para request/response
- Versionado de API

## Autor

Brewall - Proyecto de API para gestión de supermercado

## Licencia

Este proyecto es de código abierto y está disponible.
