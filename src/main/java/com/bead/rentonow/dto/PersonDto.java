package com.bead.rentonow.dto;

import com.bead.rentonow.model.Person;
import com.bead.rentonow.model.Role;
import lombok.Data;

@Data
public class PersonDto {

    private Long id;
    private Role role;
    private String fullName;

    public PersonDto(Person person) {
        id = person.getId();
        role = person.getRole();
        fullName = person.getFullName();
    }

    //mappings
    public Person getPerson() {
        Person person = new Person();
        person.setId(id);
        person.setRole(role);
        person.setFullName(fullName);

        return person;
    }
}