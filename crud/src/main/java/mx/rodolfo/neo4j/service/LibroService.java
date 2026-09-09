package mx.rodolfo.neo4j.service;

import mx.rodolfo.neo4j.dto.LibroPeticion;
import mx.rodolfo.neo4j.exception.RecursoNoEncontradoException;
import mx.rodolfo.neo4j.model.Libro;
import mx.rodolfo.neo4j.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository repository;

    public LibroService(LibroRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<Libro> listar(String titulo, String autor) {
        if (titulo != null && !titulo.isBlank()) return repository.findByTituloContainingIgnoreCase(titulo.trim());
        if (autor != null && !autor.isBlank()) return repository.findByAutorContainingIgnoreCase(autor.trim());
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> noExiste(id));
    }

    @Transactional
    public Libro crear(LibroPeticion p) {
        return repository.save(new Libro(null, p.titulo().trim(), p.autor().trim(), p.anio(), limpiar(p.isbn())));
    }

    @Transactional
    public Libro actualizar(Long id, LibroPeticion p) {
        Libro libro = obtener(id);
        libro.setTitulo(p.titulo().trim());
        libro.setAutor(p.autor().trim());
        libro.setAnio(p.anio());
        libro.setIsbn(limpiar(p.isbn()));
        return repository.save(libro);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw noExiste(id);
        repository.deleteById(id);
    }

    private String limpiar(String valor) {
        return valor == null || valor.isBlank() ? null : valor.trim();
    }

    private RecursoNoEncontradoException noExiste(Long id) {
        return new RecursoNoEncontradoException("No existe el libro con id " + id);
    }
}
