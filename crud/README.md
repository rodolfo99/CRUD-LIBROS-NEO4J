# CRUD con Spring Boot y Neo4j

API REST de libros construida con Java 21, Spring Boot 3.5.3 y Spring Data Neo4j.

## Requisitos

- Java 21 o posterior
- Maven 3.9+
- Docker con Docker Compose

## Ejecución

1. Copia las variables de ejemplo (opcional):

   ```bash
   cp .env.example .env
   ```

2. Levanta Neo4j:

   ```bash
   docker compose up -d --wait
   ```

3. Inicia Spring Boot:

   ```bash
   mvn spring-boot:run
   ```

La API queda en `http://localhost:8080/api/libros` y Neo4j Browser en
`http://localhost:7474`. Usuario predeterminado: `neo4j`; contraseña:
`password123`.

## Operaciones

```bash
# Crear
curl -i -X POST http://localhost:8080/api/libros \
  -H 'Content-Type: application/json' \
  -d '{"titulo":"Clean Code","autor":"Robert C. Martin","anio":2008,"isbn":"9780132350884"}'

# Listar / obtener uno
curl http://localhost:8080/api/libros
curl http://localhost:8080/api/libros/0

# Buscar (usar uno de los dos parámetros)
curl 'http://localhost:8080/api/libros?titulo=clean'
curl 'http://localhost:8080/api/libros?autor=martin'

# Actualizar (sustituye 0 por el id devuelto al crear)
curl -X PUT http://localhost:8080/api/libros/0 \
  -H 'Content-Type: application/json' \
  -d '{"titulo":"Código limpio","autor":"Robert C. Martin","anio":2008,"isbn":"9780132350884"}'

# Eliminar
curl -i -X DELETE http://localhost:8080/api/libros/0
```

## Endpoints

| Método | Ruta | Resultado |
|---|---|---|
| GET | `/api/libros` | Lista libros |
| GET | `/api/libros/{id}` | Obtiene un libro |
| POST | `/api/libros` | Crea un libro |
| PUT | `/api/libros/{id}` | Actualiza todo el libro |
| DELETE | `/api/libros/{id}` | Elimina un libro |

Para ejecutar las pruebas: `mvn test`.
