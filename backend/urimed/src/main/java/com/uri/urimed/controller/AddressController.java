package com.uri.urimed.controller;

import com.uri.urimed.model.Address;
import com.uri.urimed.record.AddressRegistrationData;
import com.uri.urimed.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody AddressRegistrationData data) {
        Address address = new Address(data);
        addressRepository.save(address);

        return ResponseEntity.status(HttpStatus.CREATED).body("Address saved");
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody AddressRegistrationData data) {
        Address address = new Address(data);
        addressRepository.delete(address);

        return ResponseEntity.status(HttpStatus.OK).body("Address deleted");
    }

}
