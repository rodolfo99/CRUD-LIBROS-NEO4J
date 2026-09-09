import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { finalize } from 'rxjs';
import { Libro, LibroPeticion } from './libro';
import { LibroService } from './libro.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  private readonly service = inject(LibroService);

  libros: Libro[] = [];
  formulario: LibroPeticion = this.formularioVacio();
  idEditando: number | null = null;
  filtroTitulo = '';
  filtroAutor = '';
  cargando = false;
  guardando = false;
  mensaje = '';
  error = '';

  ngOnInit(): void { this.cargar(); }

  cargar(): void {
    this.cargando = true;
    this.error = '';
    this.service.listar(this.filtroTitulo, this.filtroAutor)
      .pipe(finalize(() => this.cargando = false))
      .subscribe({
        next: libros => this.libros = libros,
        error: err => this.mostrarError(err, 'No se pudieron cargar los libros. Verifica que el backend esté en el puerto 8080.')
      });
  }

  buscar(): void {
    if (this.filtroTitulo.trim()) this.filtroAutor = '';
    this.cargar();
  }

  limpiarBusqueda(): void {
    this.filtroTitulo = '';
    this.filtroAutor = '';
    this.cargar();
  }

  guardar(form: NgForm): void {
    if (form.invalid || this.guardando) {
      form.control.markAllAsTouched();
      return;
    }
    const peticion: LibroPeticion = {
      titulo: this.formulario.titulo.trim(),
      autor: this.formulario.autor.trim(),
      anio: this.formulario.anio === null ? null : Number(this.formulario.anio),
      isbn: this.formulario.isbn?.trim() || null
    };
    const editando = this.idEditando !== null;
    const operacion = editando
      ? this.service.actualizar(this.idEditando as number, peticion)
      : this.service.crear(peticion);

    this.guardando = true;
    this.error = '';
    operacion.pipe(finalize(() => this.guardando = false)).subscribe({
      next: libroGuardado => {
        libroGuardado.id = Number(libroGuardado.id);
        this.mensaje = editando ? 'Libro actualizado correctamente.' : 'Libro creado correctamente.';
        this.cancelarEdicion(form);
        this.cargar();
      },
      error: err => this.mostrarError(err, 'No se pudo guardar el libro.')
    });
  }

  editar(libro: Libro): void {
    this.idEditando = Number(libro.id);
    this.formulario = {
      titulo: libro.titulo,
      autor: libro.autor,
      anio: libro.anio,
      isbn: libro.isbn
    };
    this.mensaje = '';
    this.error = '';
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  cancelarEdicion(form?: NgForm): void {
    this.idEditando = null;
    this.formulario = this.formularioVacio();
    form?.resetForm(this.formulario);
  }

  eliminar(libro: Libro): void {
    const id = Number(libro.id);
    if (!Number.isFinite(id)) {
      this.error = 'El libro no tiene un identificador válido.';
      return;
    }
    if (!confirm(`¿Eliminar “${libro.titulo}”?`)) return;

    this.error = '';
    this.service.eliminar(id).subscribe({
      next: () => {
        this.libros = this.libros.filter(item => Number(item.id) !== id);
        if (this.idEditando === id) this.cancelarEdicion();
        this.mensaje = 'Libro eliminado correctamente.';
      },
      error: err => this.mostrarError(err, 'No se pudo eliminar el libro.')
    });
  }

  trackById(_index: number, libro: Libro): number { return Number(libro.id); }

  private formularioVacio(): LibroPeticion {
    return { titulo: '', autor: '', anio: null, isbn: null };
  }

  private mostrarError(err: any, alternativo: string): void {
    this.mensaje = '';
    const detalle = err?.error?.detail;
    const validaciones = err?.error?.errores;
    this.error = validaciones
      ? Object.entries(validaciones).map(([campo, mensaje]) => `${campo}: ${mensaje}`).join(' · ')
      : detalle || alternativo;
  }
}
