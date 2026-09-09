package mx.rodolfo.neo4j.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LibroPeticion(
        @NotBlank @Size(max = 200) String titulo,
        @NotBlank @Size(max = 150) String autor,
        @Min(0) @Max(3000) Integer anio,
        @Size(max = 20) String isbn
) {}
