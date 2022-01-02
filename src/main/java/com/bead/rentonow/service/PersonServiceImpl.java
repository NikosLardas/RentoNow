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

        if(personDto==null) {
            responsePerson = new ApiResponse<PersonDto>(400, "person is null", null);
        } else if (personDto.getFullName() == null) {
            responsePerson = new ApiResponse<PersonDto>(401, "person full name is null", null);
        } else if (personDto.getRole() == null) {
            responsePerson = new ApiResponse<PersonDto>(401, "person role is null", null);
        } else if (personDto.getUsername() == null) {
            responsePerson = new ApiResponse<PersonDto>(401, "person username is null", null);
        } else if (personDto.getPassword() == null) {
            responsePerson = new ApiResponse<PersonDto>(401, "person password is null", null);
        } else if (personRepository.findPersonByUsername(personDto.getUsername()).isPresent()) {
            responsePerson = new ApiResponse<PersonDto>(402, "person username: " +personDto.getUsername() +" already exists", null);
        } else if (!roles.contains(personDto.getRole())) {
            responsePerson = new ApiResponse<PersonDto>(401, personDto.getRole() + " is not a valid role", null);
        }
        else {
            try {
                responsePerson = new ApiResponse<PersonDto>(200, "ok", new PersonDto(personRepository.save(personDto.getPerson())));
            } catch (Exception e) {
                responsePerson = new ApiResponse<PersonDto>(403,
                        "the person was not saved", null);
            }
        }

        return responsePerson;
    }
}