# API REST - APARTAMENTOS BLUE

## Descripción de la API
API REST desarrollada en Java con Spring Boot para digitalizar la gestión integral de rentas inmobiliarias (habitaciones, pisos y casas completas). Permite la administración de clientes (arrendatarios y propietarios), registro y control de características de propiedades y gestión de disponibilidad de fechas y precios especiales.

---

## Tecnologías Utilizadas
* **Lenguaje:** Java 21
* **Framework:** Spring Boot
  * Spring Web MVC (Endpoints RESTful)
  * Spring Data JPA / Hibernate (Capa de persistencia relacional)
  * Thymeleaf (Renderizado de vistas web)
* **Base de Datos:** MySQL Server (Driver mysql-connector-j)
* **Gestor de Compilación y Dependencias:** Apache Maven
* **Cliente de Pruebas:** Postman

---

## Variables de Entorno Necesarias
Para permitir una configuración desacoplada sin exponer contraseñas locales en el repositorio:

* **PORT:** Puerto de escucha de la aplicación (Por defecto: 8080)
* **DB_URL:** Cadena de conexión JDBC (Por defecto: jdbc:mysql://localhost:3306/apartamentos_blue?useSSL=false&serverTimezone=UTC)
* **DB_USERNAME:** Usuario de base de datos (Por defecto: root)
* **DB_PASSWORD:** Contraseña de base de datos (Por defecto: tu_contrasena)

---

## Pasos de Instalación y Ejecución Local

### Prerrequisitos
* Java JDK 21 instalado en el sistema
* MySQL Server activo en el puerto 3306
* Base de datos creada:
  CREATE DATABASE apartamentos_blue;

### Pasos de Ejecución
* 1. Clonar el repositorio:
  git clone https://github.com/ferch2709/apartamentos-API.git
  cd apartamentos-API

* 2. Configurar la base de datos:
  Verificar credenciales en src/main/resources/application.properties.

* 3. Ejecutar la aplicación:
  ./mvnw spring-boot:run
  O ejecutar ApartamentosApplication.java desde el IDE.

* 4. Acceso:
  La API estará disponible en http://localhost:8080

---

## Listado de Endpoints

### 1. Clientes (/api/clientes)
* **GET /api/clientes:** Lista todos los clientes registrados
* **POST /api/clientes:** Registra un nuevo cliente (Propietario o Arrendatario)
* **GET /api/clientes/{id}:** Obtiene un cliente por su ID
* **PUT /api/clientes/{id}:** Actualiza los datos de un cliente
* **DELETE /api/clientes/{id}:** Elimina un cliente

### 2. Propiedades (/api/propiedades)
* **GET /api/propiedades:** Lista todas las propiedades
* **POST /api/propiedades:** Da de alta una nueva propiedad
* **GET /api/propiedades/{id}:** Obtiene una propiedad por su ID
* **GET /api/propiedades/tipo/{tipo}:** Filtra propiedades por tipo (CASA, PISO, HABITACION)
* **GET /api/propiedades/propietario/{propietarioId}:** Retorna todas las propiedades de un propietario
* **PUT /api/propiedades/{id}:** Actualiza características de una propiedad
* **DELETE /api/propiedades/{id}:** Elimina una propiedad

### 3. Disponibilidad y Reservas (/api/disponibilidades)
* **GET /api/disponibilidades:** Lista todas las fechas de disponibilidad registradas
* **POST /api/disponibilidades:** Registra disponibilidad/reserva para una propiedad
* **GET /api/disponibilidades/{id}:** Obtiene un registro por su ID
* **GET /api/disponibilidades/propiedad/{idPropiedad}:** Filtra disponibilidad por propiedad
* **PUT /api/disponibilidades/{id}:** Modifica fechas o precio especial
* **DELETE /api/disponibilidades/{id}:** Elimina un registro de disponibilidad

---

## Ejemplos de Peticiones y Respuestas

### 1. Registrar Cliente Propietario
* **Método:** POST
* **URL:** http://localhost:8080/api/clientes
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "tipo": "PROPIETARIO",
  "nombres": "Roberto",
  "apellidos": "Gómez Bolaños",
  "email": "roberto.gomez@apartamentos.com",
  "telefono": "5512345678",
  "fechaNacimiento": "1985-05-15",
  "ine": "INE987654321",
  "direccion": "Av. Insurgentes Sur 123",
  "estatus": "ACTIVO",
  "password": "password123"
}
```
* **Estado HTTP:** 201 Created

---

### 2. Registrar Propiedad
* **Método:** POST
* **URL:** http://localhost:8080/api/propiedades
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "propietario": {
    "id": 1
  },
  "tipo": "PISO",
  "titulo": "Moderno Apartamento en el Centro",
  "descripcion": "Apartamento completamente amueblado con excelente vista y balcón.",
  "direccion": "Calle Reforma 404, Piso 5",
  "ciudad": "Ciudad de México",
  "entidadFederativa": "CDMX",
  "codigoPostal": 6600,
  "pais": "México",
  "latitud": 19.4326,
  "longitud": -99.1332,
  "precioNoche": 1200.0,
  "capacidad": 4,
  "numHabitaciones": 2,
  "numBanos": 2,
  "metroCuadrados": 75,
  "comodidades": "Wifi, Cocina equipada, Aire acondicionado, Estacionamiento",
  "reglas": "No fumar, No fiestas",
  "estado": "DISPONIBLE"
}
```
* **Estado HTTP:** 201 Created

---

### 3. Registrar Disponibilidad
* **Método:** POST
* **URL:** http://localhost:8080/api/disponibilidades
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "propiedad": {
    "idPropiedad": 1
  },
  "fechaInicio": "2026-11-01",
  "fechaFin": "2026-11-15",
  "disponible": true,
  "precioEspecial": 1100.0
}
```
* **Estado HTTP:** 201 Created

---

### 4. Consultar Todas las Propiedades
* **Método:** GET
* **URL:** http://localhost:8080/api/propiedades
* **Estado HTTP:** 200 OK

---

### 5. Actualizar una Propiedad
* **Método:** PUT
* **URL:** http://localhost:8080/api/propiedades/1
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "propietario": {
    "id": 1
  },
  "tipo": "PISO",
  "titulo": "Moderno Apartamento Céntrico - Remodelado",
  "descripcion": "Apartamento amueblado con balcón y terraza.",
  "direccion": "Calle Reforma 404, Piso 5",
  "ciudad": "Ciudad de México",
  "entidadFederativa": "CDMX",
  "codigoPostal": 6600,
  "pais": "México",
  "latitud": 19.4326,
  "longitud": -99.1332,
  "precioNoche": 950.0,
  "capacidad": 4,
  "numHabitaciones": 2,
  "numBanos": 2,
  "metroCuadrados": 75,
  "comodidades": "Wifi de alta velocidad, Aire acondicionado, Estacionamiento",
  "reglas": "No fumar",
  "estado": "DISPONIBLE"
}
```
* **Estado HTTP:** 200 OK

---

### 6. Eliminar Registro
* **Método:** DELETE
* **URL:** http://localhost:8080/api/disponibilidades/1
* **Estado HTTP:** 204 No Content
