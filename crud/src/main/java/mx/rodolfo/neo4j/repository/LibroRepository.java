package mx.rodolfo.neo4j.repository;

import mx.rodolfo.neo4j.model.Libro;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface LibroRepository extends Neo4jRepository<Libro, Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
}
