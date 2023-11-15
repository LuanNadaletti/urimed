package com.uri.urimed.controller;

import com.uri.urimed.model.Address;
import com.uri.urimed.repository.AddressRepository;
import com.uri.urimed.util.ListUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody @Valid Address address) {
        Address persistedAddress = addressRepository.save(address);
        URI location = URI.create("/addresses/" + persistedAddress.getId());

        return ResponseEntity.created(location).body(persistedAddress);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        if (ListUtils.isNullOrEmpty(addresses)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Integer id) {
        return addressRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable("id") Integer id) {
        try {
            addressRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
