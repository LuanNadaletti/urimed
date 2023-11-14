package com.uri.urimed.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uri.urimed.model.Address;
import com.uri.urimed.model.Doctor;
import com.uri.urimed.model.Gender;
import com.uri.urimed.model.Specialty;
import com.uri.urimed.repository.DoctorRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Date;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(DoctorController.class)
public class DoctorControllerTests {

    private static final String END_POINT_PATH = "/doctors";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DoctorRepository doctorRepository;

    @Test
    void testAddShouldReturnStatusFail() throws Exception {
        Doctor doctor = new Doctor();
        String requestBody = objectMapper.writeValueAsString(doctor);

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testAddShouldReturnStatusCreated() throws Exception {
        Doctor doctor = createSampleDoctor();
        doctor.setId(1);

        String requestBody = objectMapper.writeValueAsString(doctor);
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/doctors/1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetShouldReturnStatusOk() throws Exception {
        Doctor doctor = createSampleDoctor();
        doctor.setId(1);

        Mockito.when(doctorRepository.findById(1)).thenReturn(java.util.Optional.of(doctor));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH + "/1"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetAllShouldReturnStatusNoContent() throws Exception {
        Mockito.when(doctorRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(doctorRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetAllShouldReturnStatusOk() throws Exception {
        Mockito.when(doctorRepository.findAll()).thenReturn(Collections.singletonList(createSampleDoctor()));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private @NotNull Doctor createSampleDoctor() {
        return new Doctor("username", "password", "99999999999", "Test Test", new Date(), new Gender("M", "Masculino"), "5499999999", new Address(), "1234567", "test@test.com", new Specialty());
    }

}
