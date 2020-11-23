import { Injectable } from "@angular/core";

import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";

import { Departamento } from "../models/departamento";
import { Wrapper } from "../models/wrapper";

@Injectable({
  providedIn: "root"
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}

  inserir(departamento: Departamento): Observable<Wrapper<Departamento>> {
    return this.httpClient.post<Wrapper<Departamento>>(
      "http://localhost:4200/api/v1/departments",
      departamento
    );
  }

  atualizar(departamento: Departamento): Observable<Wrapper<Departamento>> {
    return this.httpClient.put<Wrapper<Departamento>>(
      `http://localhost:4200/api/v1/departments/${departamento.id}`,
      departamento
    );
  }

  listar(): Observable<Wrapper<Departamento[]>> {
    return this.httpClient.get<Wrapper<Departamento[]>>(
      "http://localhost:4200/api/v1/departments"
    );
  }

  deletar(departamento: Departamento): Observable<Wrapper<void>> {
    return this.httpClient.delete<Wrapper<void>>(
      `http://localhost:4200/api/v1/departments/${departamento.id}`
    );
  }
}
