package com.baeldung.bulkandbatchapi.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(@Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping(path = "/addresses")
    public List<Address> getAllAddresses() {
        return addressRepository.getAllAddresses();
    }
}
