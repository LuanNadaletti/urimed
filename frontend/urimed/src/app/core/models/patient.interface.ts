import { Address } from './address.interface';
import { Gender } from './gender.interface';
import { Role } from './role.interface';

export interface Patient {
  cpf: string;
  name: string;
  birthdate: Date;
  gender: Gender;
  phone: string;
  address: Address;
  email: string;
  username: string;
  password: string;
  role: Role;
}
