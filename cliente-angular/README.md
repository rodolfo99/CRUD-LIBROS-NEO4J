# Cliente Angular para el CRUD Neo4j

Cliente Angular 20 para la API Spring Boot disponible en `http://localhost:8080/api/libros`.

## Requisitos

- Node.js 22.12 o posterior
- Backend `crud-spring-neo4j` ejecutándose en el puerto 8080

## Ejecutar

```bash
npm install
npm start
```

Abre `http://localhost:4200`. El servidor de desarrollo usa `proxy.conf.json`, por lo que las llamadas a `/api` se redirigen al backend sin problemas de CORS.

## Funciones

- Crear libros
- Listar libros
- Buscar por título o autor
- Editar libros
- Eliminar libros con confirmación
- Validar campos obligatorios
- Mostrar errores devueltos por Spring

Si el backend se ejecuta en otro puerto, cambia `target` dentro de `proxy.conf.json`.
