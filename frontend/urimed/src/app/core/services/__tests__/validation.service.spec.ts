import { TestBed } from '@angular/core/testing';
import { FormControl } from '@angular/forms';
import { ValidationService } from '../validation.service';

describe('ValidationService', () => {
  let service: ValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('validateCpf', () => {
    it('should return null when the CPF is valid', () => {
      const control = new FormControl('12345678909');
      const result = service.validateCpf(control);
      expect(result).toBeNull();
    });

    it('should return an error object when the CPF is invalid', () => {
      const contorl = new FormControl('11111111111');
      const result = service.validateCpf(contorl);
      expect(result).toEqual({ invalidCpf: true });
    });
  });
});
