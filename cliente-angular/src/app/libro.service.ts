import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Libro, LibroPeticion } from './libro';

@Injectable({ providedIn: 'root' })
export class LibroService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = '/api/libros';

  listar(titulo = '', autor = ''): Observable<Libro[]> {
    let params = new HttpParams();
    if (titulo.trim()) params = params.set('titulo', titulo.trim());
    else if (autor.trim()) params = params.set('autor', autor.trim());
    return this.http.get<Libro[]>(this.apiUrl, { params });
  }

  crear(libro: LibroPeticion): Observable<Libro> {
    return this.http.post<Libro>(this.apiUrl, libro);
  }

  actualizar(id: number, libro: LibroPeticion): Observable<Libro> {
    return this.http.put<Libro>(`${this.apiUrl}/${id}`, libro);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
