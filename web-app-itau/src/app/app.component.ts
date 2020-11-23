import { Component, OnInit } from "@angular/core";
import { ApiService } from "./api.service";
import { Departamento } from "../models/departamento";
import { FormGroup, FormBuilder } from "@angular/forms";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  form: FormGroup;
  departamentos: Departamento[];

  constructor(private api: ApiService, private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.setupForm();
    this.carregarLista();
  }

  private setupForm(): void {
    this.form = this.formBuilder.group({
      id: null,
      code: "",
      name: "",
      local: "",
      city: "",
      state: "",
      board: ""
    });
  }

  private onGravar(departamento: Departamento) {
    let observable;
    if (departamento.id != null) {
      observable = this.api.atualizar(departamento);
    } else {
      observable = this.api.inserir(departamento);
    }
    observable.subscribe((wrapper) => {
      this.form.reset();
      this.carregarLista();
    });
  }

  private carregarLista(): void {
    this.api
      .listar()
      .subscribe((wrapper) => (this.departamentos = wrapper.data));
  }

  private onEditar(departamento: Departamento) {
    this.form.setValue(departamento);
  }

  private onDeletar(departamento: Departamento) {
    this.api.deletar(departamento).subscribe((_) => this.carregarLista());
  }
}
