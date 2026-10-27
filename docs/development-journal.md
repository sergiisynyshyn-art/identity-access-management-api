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
-------------
Día 4
Primera regla de negocio
Implementación inicial de RegisterUserUseCaseImpl.

Regla implementada:

No se permite registrar dos usuarios con el mismo email.
Aprendizajes:

Los Use Cases contienen reglas de negocio.
Los Use Cases utilizan Ports.
El dominio sigue desacoplado de PostgreSQL.
Las excepciones representan errores de negocio.
-----------
Día 5
Creación del objeto de dominio User
Implementada la transformación de RegisterUserRequest a User.

Aprendizajes:

Los DTOs representan datos externos.
El dominio representa las entidades del negocio.
El Use Case es responsable de transformar DTOs en objetos de dominio.
Builder simplifica la construcción de objetos complejos.
Persistencia mediante Ports
Se ha añadido la llamada:

userRepositoryPort.save(user)

Aprendizajes:

Los Use Cases no guardan directamente en PostgreSQL.
La persistencia se realiza mediante Ports.
save() devuelve la entidad persistida.
El identificador suele generarse después del guardado.
-------
Construcción de respuestas
Implementado UserResponse en RegisterUserUseCaseImpl.

Aprendizajes:

El dominio no debe exponerse directamente.
Los DTOs controlan la información enviada al cliente.
UserResponse evita exponer campos sensibles como password.
-----
Día 6
Entendiendo save()
Aprendizajes:

save() devuelve la entidad persistida.
El identificador puede generarse después del guardado.
Por este motivo se utiliza savedUser en lugar del objeto original.
El caso de uso continúa desacoplado de PostgreSQL gracias al uso de UserRepositoryPort.
-----
Día 6
Domain Model vs Persistence Entity
Se ha creado UserEntity.

Aprendizajes:

User representa el negocio.
UserEntity representa la persistencia.
El dominio permanece independiente de JPA.
La arquitectura hexagonal separa negocio y base de datos.
---------
Día 7
UserJpaRepository
Se ha creado el primer repositorio JPA del proyecto.

Aprendizajes:

JpaRepository proporciona operaciones CRUD automáticamente.
UserJpaRepository trabaja sobre UserEntity.
El dominio sigue desacoplado de JPA.
UserRepositoryPort continúa siendo el contrato principal de la aplicación.
----------

Día 8
UserMapper
Se ha creado UserMapper utilizando MapStruct.

Aprendizajes:

User representa el dominio.
UserEntity representa la persistencia.
UserMapper transforma objetos entre ambas capas.
MapStruct genera automáticamente el código de conversión.

dia 9
Día 9
UserPersistenceAdapter
Se ha creado el primer adaptador de persistencia.

Aprendizajes:

Un Adapter implementa un Port.
El Adapter conecta dominio e infraestructura.
UserMapper transforma User ↔ UserEntity.
JpaRepository realiza la persistencia real.
Los Use Cases continúan desacoplados de PostgreSQL.
-----
Día 10
Implementación de existsByEmail
Se ha implementado existsByEmail en UserPersistenceAdapter.

Aprendizajes:

El Adapter implementa los métodos definidos en el Port.
UserJpaRepository realiza la consulta real.
El caso de uso sigue desacoplado de la capa de persistencia.
Se completa el flujo UseCase → Port → Adapter → Repository.
