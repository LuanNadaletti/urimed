package com.uri.urimed.controller;

import com.uri.urimed.model.Address;
import com.uri.urimed.record.AddressRegistrationData;
import com.uri.urimed.repository.AddressRepository;
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

@SpringBootTest
public class AddressControllerTests {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        AddressRegistrationData registrationData = createSampleRegistrationData();
        Address address = new Address(registrationData);

        Mockito.when(addressRepository.save(address)).thenReturn(address);

        ResponseEntity<String> response = addressController.save(registrationData);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals("Address saved", response.getBody());
    }

    @Test
    void testDelete() {
        AddressRegistrationData registrationData = createSampleRegistrationData();
        Address address = new Address(registrationData);

        Mockito.doNothing().when(addressRepository).delete(address);

        ResponseEntity<String> response = addressController.delete(registrationData);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Address deleted", response.getBody());
    }

    private AddressRegistrationData createSampleRegistrationData() {
        return new AddressRegistrationData("Test Street", "9999999", "Test Neighborhood", "Test City", "Test State", "99999999");
    }

}
