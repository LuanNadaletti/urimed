package com.uri.urimed.configuration;

import com.uri.urimed.model.Gender;
import com.uri.urimed.model.Role;
import com.uri.urimed.model.Specialty;
import com.uri.urimed.repository.GenderRepository;
import com.uri.urimed.repository.RoleRepository;
import com.uri.urimed.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.saveAll(createRoles());
            }

            if (genderRepository.count() == 0) {
                genderRepository.saveAll(createGenders());
            }

            if (specialtyRepository.count() == 0) {
                specialtyRepository.saveAll(createSpecialties());
            }
        };
    }

    private List<Role> createRoles() {
        Role role1 = new Role();
        role1.setName("admin");

        Role role2 = new Role();
        role2.setName("doctor");

        Role role3 = new Role();
        role3.setName("patient");

        return Arrays.asList(role1, role2, role3);
    }

    private List<Gender> createGenders() {
        Gender gender1 = new Gender();
        gender1.setId("M");
        gender1.setName("Masculino");

        Gender gender2 = new Gender();
        gender2.setId("F");
        gender2.setName("Feminino");

        return Arrays.asList(gender1, gender2);
    }

    private List<Specialty> createSpecialties() {
        Specialty specialty1 = new Specialty();
        specialty1.setName("Ortopedia");

        Specialty specialty2 = new Specialty();
        specialty2.setName("Cardiologia");

        Specialty specialty3 = new Specialty();
        specialty3.setName("Ginecologia");

        Specialty specialty4 = new Specialty();
        specialty4.setName("Urologia");

        return Arrays.asList(specialty1, specialty2, specialty3, specialty4);
    }
}
