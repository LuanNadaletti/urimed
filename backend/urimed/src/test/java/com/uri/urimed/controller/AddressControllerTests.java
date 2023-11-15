package com.uri.urimed.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uri.urimed.model.Address;
import com.uri.urimed.repository.AddressRepository;
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

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AddressController.class)
public class AddressControllerTests {

    private static final String END_POINT_PATH = "/addresses";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressRepository addressRepository;

    @Test
    void testAddShouldReturnStatusFail() throws Exception {
        Address address = new Address();
        String requestBody = objectMapper.writeValueAsString(address);

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testAddShouldReturnStatusCreated() throws Exception {
        Address address = createSampleAddress();
        address.setId(1);

        String requestBody = objectMapper.writeValueAsString(address);
        Mockito.when(addressRepository.save(address)).thenReturn(address);

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/addresses/1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetShouldReturnStatusOk() throws Exception {
        Address address = createSampleAddress();
        address.setId(1);

        Mockito.when(addressRepository.findById(1)).thenReturn(java.util.Optional.of(address));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH + "/1"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetAllShouldReturnStatusNoContent() throws Exception {
        Mockito.when(addressRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(addressRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetAllShouldReturnStatusOk() throws Exception {
        Mockito.when(addressRepository.findAll()).thenReturn(Collections.singletonList(createSampleAddress()));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private Address createSampleAddress() {
        return new Address("Rua 1", "123", "Bairro 1", "Cidade 1", "Estado 1", "12345678");
    }

}
