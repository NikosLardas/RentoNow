package com.bead.rentonow.service;

import com.bead.rentonow.dto.PropertyDto;
import com.bead.rentonow.dto.PropertyInfoDto;
import com.bead.rentonow.exception.PersonNotFoundException;
import com.bead.rentonow.front.ApiResponse;
import com.bead.rentonow.model.Person;
import com.bead.rentonow.model.Property;
import com.bead.rentonow.repository.PersonRepository;
import com.bead.rentonow.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    PropertyRepository propertyRepository;
    PersonRepository personRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PersonRepository personRepository) {
        this.propertyRepository = propertyRepository;
        this.personRepository = personRepository;
    }

    // get all properties (with fewer details)
    @Override
    public ApiResponse<List<PropertyDto>> read() {
        return new ApiResponse<List<PropertyDto>>(200, "ok", propertyRepository
                .findAll()
                .stream()
                .map(PropertyDto::new)
                .collect(Collectors.toList()));
    }

    // get one property (with all details)
    @Override
    public ApiResponse<PropertyInfoDto> read(Long id){
        Optional<Property> oProperty = propertyRepository.findById(id);
        if (oProperty.isPresent()) {
            return new ApiResponse<PropertyInfoDto>(200, "ok", new PropertyInfoDto(oProperty.get()));
        }
        return new ApiResponse<PropertyInfoDto>(400, "not found", null);
    }

    // create one property
    @Override
    public ApiResponse<PropertyInfoDto> create(PropertyInfoDto propertyInfoDto, Long personId) throws PersonNotFoundException {
        Optional<Person> oPerson = personRepository.findById(personId);
        if (!oPerson.isPresent()) {
            throw new PersonNotFoundException("Person with ID: " + personId + " not found !");
        }

        Property property = propertyInfoDto.getProperty();
        property.setHost(oPerson.get());

        return new ApiResponse<PropertyInfoDto>(201,"ok", new PropertyInfoDto(propertyRepository.save(property)));
    }
}