package com.bead.rentonow.service;

import com.bead.rentonow.dto.PersonDto;
import com.bead.rentonow.front.ApiResponse;

import java.util.Arrays;
import java.util.List;

public interface PersonService {

    List<String> roles = Arrays.asList("ROLE_ADMIN","ROLE_HOST","ROLE_GUEST");

    ApiResponse<List<PersonDto>> read();
    ApiResponse<PersonDto> create(PersonDto personDto);
}
