export interface Libro {
  id: number;
  titulo: string;
  autor: string;
  anio: number | null;
  isbn: string | null;
}

export type LibroPeticion = Omit<Libro, 'id'>;
