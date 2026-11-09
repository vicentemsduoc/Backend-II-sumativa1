# Desarrollo Backend II: Clínica Petsalud

Este repositorio corresponde a la actividad sumativa de la semana 3 del ramo "Desarrollo Backend II".
El proyecto fue desarrollado con Spring Boot 3.3.6, y se centra en implementar autenticación y autorización con "Spring Security y JWT" junto con un control de acceso basado en roles, siendo construido en base al proyecto entregado en la semana 1, sumado de las implementaciones de nuevas tecnologías y prácticas enseñadas en las semanas siguientes.


## Tecnologías a destacar:

- Java 17
- Spring Boot 3.3.6
- Spring Security
- Spring Data JPA
- JWT (jjwt 0.12.6)
- MySQL 8
- Docker Compose
- Maven


## Estructura del proyecto:

src/main/java/com/petsalud/backendpetsalud/
├── controller/     # Endpoints REST
├── dto/            # Objetos de transferencia
├── entity/         # Entidades JPA
├── repository/     # Repositorios
├── service/        # Lógica de negocio
└── security/       # Configuración de seguridad (JWT, UserDetails, filtros)


## Configuración de seguridad

La seguridad está centralizada en "SecurityConfig.java" y utiliza:

- **Autenticación**: JWT (stateless)
- **Autorización**: Basada en roles (`ADMIN`, `VETERINARIO`)
- **Contraseñas**: BCrypt
- **Filtro JWT**: "JwtAuthFilter" valida el token en cada request

### Endpoints principales

- POST: /api/usuarios/registro (Público) 
- POST: /api/login (Público)   
- GET: /api/mascotas/** (Autenticado)   
- GET: /api/expedientes/** (VETERINARIO / ADMIN) 
- *: /api/usuarios/** (ADMIN)            


## Cómo ejecutar el proyecto

### 1. Levantar la base de datos:

```bash
docker-compose up -d
```

### 2. Ejecutar la aplicación Spring:

```bash
./mvnw spring-boot:run
```
(La aplicación quedará disponible en el puerto http://localhost:8989)
