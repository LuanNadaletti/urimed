import { TestBed } from "@angular/core/testing";
import { ErrorHandlingService } from "../errorhandling.service"
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ToastrService } from "ngx-toastr";

describe('ErrorHandlingService', () => {
    let service: ErrorHandlingService;
    let toastr: ToastrService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                ErrorHandlingService,
                { provide: ToastrService, useValue: jasmine.createSpyObj('NotificationService', ['error']) }
            ]
        });

        service = TestBed.inject(ErrorHandlingService);
        toastr = TestBed.inject(ToastrService);
    })

    it ('should be created', () => {
        expect(service).toBeTruthy();
    });

    it ('should handle form group errors', () => {
        const formGroup = new FormGroup({
            email: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', [Validators.required])
        });

        formGroup.setValue({ email: '', password: '' });

        service.handleFormGroupErrors(formGroup);

        expect(toastr.error).toHaveBeenCalledTimes(2);
        expect(toastr.error).toHaveBeenCalledWith('O campo email é obrigatório.');
        expect(toastr.error).toHaveBeenCalledWith('O campo password é obrigatório.');
    })
});