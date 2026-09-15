# CRUD de Libros — Spring Boot + Neo4j + Angular

> Aplicación **full stack en Java** para administrar libros con **Spring Boot**, **Spring Data Neo4j**, **Neo4j**, **Angular** y **Docker Compose**.

![Java](https://img.shields.io/badge/Java-21-informational)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-informational)
![Neo4j](https://img.shields.io/badge/Neo4j-5.26-informational)
![Angular](https://img.shields.io/badge/Angular-20.3-informational)
![Docker](https://img.shields.io/badge/Docker-Compose-informational)

Este repositorio muestra una implementación clara de un **CRUD con Spring Boot y Neo4j**, acompañado de un cliente Angular. Es una base útil para aprender Spring Data Neo4j, bases de datos de grafos, APIs REST y desarrollo full stack con Java.

## Lo más importante

- Backend REST con Spring Boot y Java 21.
- Persistencia con Spring Data Neo4j.
- Neo4j Community como base de datos de grafos.
- Cliente Angular separado del backend.
- CRUD completo de libros.
- Validación de datos.
- Ejecución sencilla con Docker Compose.
- Base preparada para evolucionar hacia relaciones entre autores, editoriales, categorías, préstamos o recomendaciones.

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

## Arquitectura

```text
Angular
   │ HTTP / REST
   ▼
Spring Boot
   │ Spring Data Neo4j
   ▼
Neo4j Graph Database
```

## Estructura

```text
.
├── crud/             # Backend Spring Boot + Neo4j
└── cliente-angular/  # Frontend Angular
```

## Inicio rápido

### 1. Neo4j

```bash
cd crud
docker compose up -d
```

Servicios:

- Neo4j Browser: `http://localhost:7474`
- Bolt: `bolt://localhost:7687`
- Usuario: `neo4j`
- Contraseña por defecto: `password123`

### 2. Backend Spring Boot

```bash
cd crud
mvn spring-boot:run
```

API principal:

```text
http://localhost:8080/api/libros
```

También puedes generar el JAR:

```bash
mvn clean package
java -jar target/crud-spring-neo4j-1.0.0.jar
```

Variables disponibles:

```text
NEO4J_URI
NEO4J_USERNAME
NEO4J_PASSWORD
SERVER_PORT
```

### 3. Cliente Angular

```bash
cd cliente-angular
npm install
npm start
```

Abre:

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
- Docker Compose

## Por qué Neo4j

Neo4j es una base de datos orientada a grafos. Aunque el ejemplo parte de libros, puede extenderse naturalmente a modelos como:

```text
(Autor)-[:ESCRIBIO]->(Libro)
(Libro)-[:PERTENECE_A]->(Categoria)
(Usuario)-[:PRESTAMO]->(Libro)
(Libro)-[:RELACIONADO_CON]->(Libro)
```

Eso hace que el repositorio sea una buena introducción a **Spring Data Neo4j**, Cypher y aplicaciones donde las relaciones entre entidades son fundamentales.

## Otros proyectos del mismo perfil

- [CRUD PostgreSQL + Angular](https://github.com/rodolfo99/CRUD-PstgreSQL-Libros-con-cliente-angular)
- [CRUD Apache Solr + Angular](https://github.com/rodolfo99/CRUD-Solr-con-cliente-angular)
- [CRUD GraphQL + PostgreSQL + Angular](https://github.com/rodolfo99/crud-GraphQL-con-cliente-angular)
- [Spring Data GraphDB](https://github.com/rodolfo99/Spring-Data-GraphDB)
- [Marc2BF — MARC21 a BIBFRAME](https://github.com/rodolfo99/Marc2BF)

## Autor

**Rodolfo Valencia** — desarrollo de software, Java, Spring, Angular, bases de datos, tecnologías semánticas e inteligencia artificial.

GitHub: [@rodolfo99](https://github.com/rodolfo99)

## Temas relacionados

Spring Boot · Spring Data Neo4j · Neo4j · graph database · Cypher · Angular · Java · TypeScript · REST API · CRUD · Docker · Maven · full stack
