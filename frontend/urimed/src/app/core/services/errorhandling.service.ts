import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class ErrorHandlingService {
  constructor(private toastr: ToastrService) {}

  errorMessages = {
    required: (controlName: string) => `O campo ${controlName} é obrigatório.`,
    minlength: (controlName: string, error: any) =>
      `O campo ${controlName} deve ter no mínimo ${error.requiredLength} caracteres.`,
    maxlength: (controlName: string, error: any) =>
      `O campo ${controlName} deve ter no máximo ${error.requiredLength} caracteres.`,
    email: (controlName: string) =>
      `O campo ${controlName} deve ser um e-mail válido.`,
    invalidCpf: () => `O CPF informado é inválido.`,
  };

  handleFormGroupErrors(formGroup: FormGroup): void {
    Object.keys(formGroup.controls).forEach((controlName) => {
      const control = formGroup.get(controlName);
      if (control && control.errors) {
        Object.keys(control.errors).forEach((error) => {
          const errorMessage = this.errorMessages[
            error as keyof typeof this.errorMessages
          ]
            ? this.errorMessages[error as keyof typeof this.errorMessages](
                controlName,
                control.errors?.[error]
              )
            : 'Erro desconhecido';

          this.toastr.error(errorMessage);
        });
      }
    });
  }
}
