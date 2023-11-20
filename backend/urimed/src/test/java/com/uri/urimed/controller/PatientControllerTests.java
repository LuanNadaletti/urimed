package com.uri.urimed.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uri.urimed.model.Address;
import com.uri.urimed.model.Gender;
import com.uri.urimed.model.Patient;
import com.uri.urimed.model.Role;
import com.uri.urimed.repository.PatientRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Date;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PatientController.class)
public class PatientControllerTests {

    private static final String END_POINT_PATH = "/patients";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testAddShouldReturnStatusFail() throws Exception {
        Patient patient = new Patient();
        String requestBody = objectMapper.writeValueAsString(patient);

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testAddShouldReturnStatusCreated() throws Exception {
        Patient patient = createSamplePatient();
        patient.setId(1);

        Mockito.when(bCryptPasswordEncoder.encode(patient.getPassword())).thenReturn(patient.getPassword());
        patient.setRole(new Role(3, "patient"));

        String requestBody = objectMapper.writeValueAsString(patient);

        Mockito.when(patientRepository.save(patient)).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/patients/1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetShouldReturnStatusOk() throws Exception {
        Patient patient = createSamplePatient();
        patient.setId(1);

        Mockito.when(patientRepository.findById(1)).thenReturn(java.util.Optional.of(patient));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH + "/1"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetAllShouldReturnStatusNoContent() throws Exception {
        Mockito.when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetAllShouldReturnStatusOk() throws Exception {
        Mockito.when(patientRepository.findAll()).thenReturn(Collections.singletonList(createSamplePatient()));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private @NotNull Patient createSamplePatient() {
        Address address = new Address("Test Street", "9999999", "Test Neighborhood", "Test City", "Test State", "99999999");

        return new Patient("username", "password", "99999999999", "Test Test", new Date(), new Gender("M", "Masculino"), "999999999", address, "test@mail.com");
    }
}
