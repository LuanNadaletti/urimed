package com.uri.urimed.record;

import com.uri.urimed.model.Address;
import com.uri.urimed.model.Specialty;

import java.util.Date;

public record DoctorRegistrationData(Date birthdate, String cpf, String gender, String name, String phone,
                                     Address address,
                                     String crm, String email, Specialty specialty) {
}
