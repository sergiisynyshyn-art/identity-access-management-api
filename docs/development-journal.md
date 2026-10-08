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