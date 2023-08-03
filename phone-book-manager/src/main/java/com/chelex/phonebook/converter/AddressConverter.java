package com.chelex.phonebook.converter;

import com.chelex.phonebook.domain.dto.AddressDto;
import com.chelex.phonebook.domain.entity.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressConverter {

    public AddressDto convert(Address address) {
        if (address == null) {
            return null;
        }

        return AddressDto.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .postalCode(address.getPostalCode())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }
}
