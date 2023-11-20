import { Address } from './address.interface';
import { Gender } from './gender.interface';
import { Role } from './role.interface';
import { Specialty } from './specialty.interface';

export interface Doctor {
  cpf: string;
  name: string;
  birthdate: Date;
  gender: Gender;
  phone: string;
  address: Address;
  crm: string;
  email: string;
  specialty: Specialty;
  username: string;
  password: string;
  role: Role;
}
