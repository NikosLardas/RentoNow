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
    private String username;
    private char[] password;
    @JsonIgnore
    private final String notVisible = "Sorry, that's a secret!";

    public PersonDto(Person person) {
        id = person.getId();
        role = person.getRole();
        fullName = person.getFullName();
        username = notVisible;
        password = notVisible.toCharArray();

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