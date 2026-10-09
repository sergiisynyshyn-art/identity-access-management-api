Diario de Desarrollo
Día 1
Objetivos completados
Configuración inicial del proyecto.
Integración con PostgreSQL.
Configuración de Flyway.
Configuración de MapStruct.
Configuración de Lombok.
Creación de la estructura hexagonal.
Conceptos aprendidos
Lombok
Permite generar automáticamente:

Getters
Setters
Constructores
Builders
Reduce código repetitivo.

Arquitectura Hexagonal
Separa:

Dominio
Casos de uso
Infraestructura
El dominio no depende de Spring ni de la base de datos.

Próximos pasos
Diseñar puertos (Ports).
Implementar UserRepositoryPort.
Implementar RoleRepositoryPort.

Día 2 - Arquitectura Hexagonal y Dominio
Objetivos completados
Arquitectura
Creación de la estructura principal del proyecto siguiendo Arquitectura Hexagonal.
Separación de capas:
Domain
Application
Infrastructure
Config
Dominio

Creación de las entidades de dominio:

User
Role
Permission

Estas clases representan el modelo de negocio y no dependen de Spring ni de la base de datos.

Lombok

Configuración y aprendizaje de Lombok.

Anotaciones utilizadas:

Java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

Aprendizajes:

Lombok reduce código repetitivo.
Genera getters y setters automáticamente.
Builder permite crear objetos más legibles.
Las anotaciones funcionan durante la compilación.

Ejemplo:

Java
User user = User.builder()
.email("test@test.com")
.enabled(true)
.build();
Ports (Arquitectura Hexagonal)

Creación de interfaces para acceso a datos.

UserRepositoryPort

Responsabilidades:

Guardar usuarios.
Buscar usuarios por id.
Buscar usuarios por email.
Comprobar existencia de email.
RoleRepositoryPort

Responsabilidades:

Guardar roles.
Buscar roles por nombre.

Aprendizajes:

El dominio no conoce PostgreSQL.
El dominio no conoce JPA.
El dominio solo conoce interfaces (Ports).
Las implementaciones llegarán más adelante mediante adaptadores.

Flujo conceptual:

Plain Text
Domain
↓
Port
↓
Adapter
↓
JPA
↓
PostgreSQL
Application Layer

Creación de los primeros DTOs.

RegisterUserRequest

Datos necesarios para registrar un usuario.

Campos:

Plain Text
email
password
UserResponse

Datos devueltos al cliente.

Campos:

Plain Text
id
email

Aprendizaje:

No debemos devolver entidades directamente.
Los DTOs protegen información sensible como passwords.
Primer Use Case

Creación de:

Java
RegisterUserUseCase

Responsabilidad:

Plain Text
Registrar un nuevo usuario.

Método:

Java
UserResponse register(RegisterUserRequest request);

Aprendizaje:

Los casos de uso representan acciones del sistema.
Definen qué hace la aplicación.
No contienen aún detalles de infraestructura.
Estado actual del proyecto

✅ Setup Spring Boot

✅ PostgreSQL

✅ Flyway

✅ Maven

✅ MapStruct

✅ Lombok

✅ Arquitectura Hexagonal

✅ Entidades de dominio

✅ Ports

✅ DTOs

✅ Primer Use Case

Próximos pasos
Fase 2B - Continuación
Crear RegisterUserUseCaseImpl
Implementar reglas de negocio
Comprobar existencia de email
Lanzar excepciones de dominio
Crear usuario
Guardar usuario mediante UserRepositoryPort
Devolver UserResponse
Próximamente
Persistencia JPA
Adaptadores
Registro real de usuarios
JWT
Spring Security

-------------
Día 3
RegisterUserUseCaseImpl
Se ha creado la implementación inicial del caso de uso de registro de usuarios.

Aprendizajes:

Un Use Case implementa una acción de negocio.
Los Use Cases dependen de Ports.
Los Use Cases no conocen PostgreSQL ni JPA.
La dependencia se realiza mediante interfaces.
Concepto clave:

Application ↓ Port ↓ Adapter ↓ Database