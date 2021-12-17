package com.bead.rentonow.service;

import com.bead.rentonow.dto.PersonDto;
import com.bead.rentonow.front.ApiResponse;

import java.util.List;

public interface PersonService {

    ApiResponse<List<PersonDto>> read();
    ApiResponse<PersonDto> create(PersonDto personDto);
}
