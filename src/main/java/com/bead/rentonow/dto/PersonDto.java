package com.bead.rentonow.dto;

import com.bead.rentonow.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class PersonDto {

    private Long id;
    private String role;
    private String fullName;
    private String username;
    private String password;
    private boolean enabled;
    @JsonIgnore
    private final String notVisible = "Sorry, that's a secret!";

    public PersonDto(Person person) {
        id = person.getId();
        role = person.getRole();
        fullName = person.getFullName();
        username = notVisible;
        password = notVisible;
        enabled = person.isEnabled();

    }

    //mappings
    @JsonIgnore
    public Person getPerson() {
        Person person = new Person();
        person.setId(id);
        person.setRole(role);
        person.setFullName(fullName);
        person.setUsername(username);
        person.setPassword(new BCryptPasswordEncoder().encode(password));
        person.setEnabled(enabled);

        return person;
    }
}