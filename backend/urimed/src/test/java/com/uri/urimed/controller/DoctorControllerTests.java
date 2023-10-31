package com.uri.urimed.controller;

import com.uri.urimed.model.Address;
import com.uri.urimed.model.Doctor;
import com.uri.urimed.model.Specialty;
import com.uri.urimed.record.DoctorRegistrationData;
import com.uri.urimed.repository.DoctorRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
public class DoctorControllerTests {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        DoctorRegistrationData testData = createSampleRegistrationData();
        Doctor doctor = new Doctor(testData);

        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);

        ResponseEntity<String> response = doctorController.save(testData);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals("Doctor saved", response.getBody());
    }

    @Test
    public void testDelete() {
        DoctorRegistrationData testData = createSampleRegistrationData();
        Doctor doctor = new Doctor(testData);

        Mockito.doNothing().when(doctorRepository).delete(doctor);

        ResponseEntity<String> response = doctorController.delete(testData);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Doctor deleted", response.getBody());
    }

    private @NotNull DoctorRegistrationData createSampleRegistrationData() {
        return new DoctorRegistrationData(new Date(), "99999999999", "M", "Test Test", "5499999999", new Address(), "1234567", "test@test.com", new Specialty());
    }

}
