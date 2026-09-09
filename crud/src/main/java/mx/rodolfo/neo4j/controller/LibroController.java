package mx.rodolfo.neo4j.controller;

import jakarta.validation.Valid;
import mx.rodolfo.neo4j.dto.LibroPeticion;
import mx.rodolfo.neo4j.model.Libro;
import mx.rodolfo.neo4j.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "${APP_CORS_ORIGINS:http://localhost:4200}")
public class LibroController {
    private final LibroService service;

    public LibroController(LibroService service) { this.service = service; }

    @GetMapping
    public List<Libro> listar(@RequestParam(required = false) String titulo,
                              @RequestParam(required = false) String autor) {
        return service.listar(titulo, autor);
    }

    @GetMapping("/{id}")
    public Libro obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody LibroPeticion peticion) {
        Libro creado = service.crear(peticion);
        return ResponseEntity.created(URI.create("/api/libros/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @Valid @RequestBody LibroPeticion peticion) {
        return service.actualizar(id, peticion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
