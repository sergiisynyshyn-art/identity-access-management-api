# Nota de inicialización de base de datos

## Diagnóstico

Se revisó el arranque de la aplicación y la causa principal por la que no se crean tablas es la configuración de Hibernate en `src/main/resources/application.yml`.

La configuración original tenía:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: none
```

Esto desactiva la creación automática del esquema por parte de Hibernate. En este proyecto además se usa Flyway:

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
```

Y existe la migración inicial en:

`src/main/resources/db/migration/V1__init.sql`

Eso indica que el patrón esperado es crear la base de datos mediante migraciones versionadas, no con `ddl-auto` de Hibernate.

## Ajuste aplicado

Se dejó la configuración en un modelo seguro y recomendado para entornos reales:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/iam_db
    username: postgres
    password: 1234

  flyway:
    enabled: true
    locations: classpath:db/migration

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
```

## ¿Por qué esta configuración?

- `ddl-auto: validate` permite que Hibernate verifique que el esquema de la base de datos coincide con las entidades.
- Flyway mantiene el control del schema mediante migraciones versionadas.
- Este enfoque evita que Hibernate cambie la estructura de la base de datos sin control al arrancar la app.

## Recomendación importante

Para que Flyway ejecute correctamente la migración:

1. La base de datos `iam_db` debe existir en PostgreSQL.
2. El usuario configurado debe tener permisos para crear/alterar tablas.
3. La migración `V1__init.sql` debe ejecutarse sin errores.

## Resultado esperado

Con esta configuración, la aplicación no intentará recrear tablas de forma automática, pero sí validará el esquema y aplicará la migración de base de datos correcta usando Flyway.
