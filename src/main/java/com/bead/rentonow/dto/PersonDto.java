package com.bead.rentonow.dto;

import com.bead.rentonow.model.Person;
import com.bead.rentonow.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDto {

    private Long id;
    private Role role;
    private String fullName;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private char[] password;

    public PersonDto(Person person) {
        id = person.getId();
        role = person.getRole();
        fullName = person.getFullName();
    }

    //mappings
    @JsonIgnore
    public Person getPerson() {
        Person person = new Person();
        person.setId(id);
        person.setRole(role);
        person.setFullName(fullName);
        person.setUsername(username);
        person.setPassword(password);

        return person;
    }
}