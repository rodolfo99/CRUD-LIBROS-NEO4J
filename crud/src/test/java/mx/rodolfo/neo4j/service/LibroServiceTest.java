package mx.rodolfo.neo4j.service;

import mx.rodolfo.neo4j.dto.LibroPeticion;
import mx.rodolfo.neo4j.exception.RecursoNoEncontradoException;
import mx.rodolfo.neo4j.model.Libro;
import mx.rodolfo.neo4j.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {
    @Mock LibroRepository repository;
    @InjectMocks LibroService service;

    @Test
    void creaLibroLimpiandoTextos() {
        when(repository.save(any())).thenAnswer(i -> {
            Libro l = i.getArgument(0); l.setId(1L); return l;
        });
        Libro creado = service.crear(new LibroPeticion("  Dune ", " Frank Herbert ", 1965, " "));
        assertEquals(1L, creado.getId());
        assertEquals("Dune", creado.getTitulo());
        assertNull(creado.getIsbn());
    }

    @Test
    void fallaAlBuscarIdInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RecursoNoEncontradoException.class, () -> service.obtener(99L));
    }
}
