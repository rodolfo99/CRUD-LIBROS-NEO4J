# CRUD de Libros con Spring Boot, Neo4j y Angular

Proyecto full stack de ejemplo para administrar un catálogo de libros usando **Spring Boot**, **Spring Data Neo4j**, **Neo4j Community** y un cliente web desarrollado con **Angular**.

El backend expone una API REST para realizar operaciones CRUD sobre nodos de libros almacenados en Neo4j, mientras que el frontend Angular permite gestionar el catálogo desde el navegador.

## Tecnologías

- Java 21
- Spring Boot 3.5.3
- Spring Web
- Spring Data Neo4j
- Neo4j 5.26 Community
- Bean Validation
- Maven
- Docker Compose
- Angular 20.3
- TypeScript 5.9
- RxJS

## Estructura del repositorio

```text
.
├── crud/             # Backend Spring Boot + Neo4j
└── cliente-angular/  # Frontend Angular
```

## Backend

El backend expone una API REST para crear, consultar, actualizar y eliminar libros almacenados en Neo4j.

Ruta principal:

```text
http://localhost:8080/api/libros
```

Cada libro contiene campos como:

```text
id
titulo
autor
anio
```

## Ejecutar Neo4j con Docker

```bash
cd crud
docker compose up -d
```

La configuración incluida utiliza:

- Neo4j Browser: `http://localhost:7474`
- Bolt: `bolt://localhost:7687`
- Usuario: `neo4j`
- Contraseña por defecto: `password123`

Los datos y logs se conservan mediante volúmenes Docker.

## Ejecutar Spring Boot

```bash
cd crud
mvn spring-boot:run
```

También puedes generar el JAR:

```bash
mvn clean package
java -jar target/crud-spring-neo4j-1.0.0.jar
```

La aplicación puede configurarse mediante variables de entorno:

```text
NEO4J_URI
NEO4J_USERNAME
NEO4J_PASSWORD
SERVER_PORT
```

## Cliente Angular

```bash
cd cliente-angular
npm install
npm start
```

Después abre:

```text
http://localhost:4200
```

## Funcionalidades

- Crear libros
- Listar libros
- Consultar libros por ID
- Actualizar libros
- Eliminar libros
- Persistencia en Neo4j
- API REST con Spring Boot
- Cliente web Angular
- Validación de datos
- Ejecución local con Docker Compose

## ¿Por qué Neo4j?

Neo4j es una base de datos orientada a grafos. Aunque este ejemplo utiliza un modelo sencillo de libros, la misma base puede extenderse para representar relaciones como autores, editoriales, categorías, préstamos o recomendaciones.

Esto permite evolucionar el CRUD hacia casos de uso donde las relaciones entre entidades sean tan importantes como los propios datos.

## Objetivo del proyecto

Mostrar una implementación clara de un **CRUD con Spring Boot, Spring Data Neo4j y Angular**, incluyendo persistencia en una base de datos de grafos, API REST, Docker y frontend separado.

## Temas relacionados

Spring Boot, Spring Data Neo4j, Neo4j, graph database, base de datos de grafos, Angular, Java, TypeScript, REST API, CRUD, Docker, Maven, desarrollo full stack.
