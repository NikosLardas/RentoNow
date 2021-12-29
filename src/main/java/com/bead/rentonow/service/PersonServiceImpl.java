package com.bead.rentonow.service;

import com.bead.rentonow.dto.PersonDto;
import com.bead.rentonow.front.ApiResponse;
import com.bead.rentonow.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // get all persons
    @Override
    public ApiResponse<List<PersonDto>> read() {
        return new ApiResponse<List<PersonDto>>(200, "ok", personRepository
                .findAll()
                .stream()
                .map(PersonDto::new)
                .collect(Collectors.toList()));
    }

    // create a person
    @Override
    public ApiResponse<PersonDto> create(PersonDto personDto) {
        ApiResponse<PersonDto> responsePerson;

        if (personDto.getRole() == null) {
            responsePerson = new ApiResponse<PersonDto>(401, "no role was provided", null);
        } else {
            responsePerson = new ApiResponse<PersonDto>(200, "ok", new PersonDto(personRepository.save(personDto.getPerson())));
        }
        return responsePerson;
    }
}