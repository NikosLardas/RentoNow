package com.bead.rentonow.service;

import com.bead.rentonow.dto.PersonDto;
import com.bead.rentonow.front.ApiResponse;
import com.bead.rentonow.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class PersonServiceImplementationTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService = new PersonServiceImpl(personRepository);

    @Test
    void testNonCreatingUserWithoutRole() {
        String password  = "testPassword";

        PersonDto person = new PersonDto();
        person.setFullName("Test Person");
        person.setUsername("testPerson");
        person.setPassword(password.toCharArray());

        ApiResponse<PersonDto> result = personService.create(person);

        assertSame("no role was provided", result.getDescription(), "The description is different from what was expected!");
        assertSame(null, result.getData(),"The data are different from what was expected!");
    }
}