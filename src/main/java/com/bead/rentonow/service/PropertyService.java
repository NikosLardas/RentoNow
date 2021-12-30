package com.bead.rentonow.service;

import com.bead.rentonow.dto.PropertyDto;
import com.bead.rentonow.dto.PropertyInfoDto;
import com.bead.rentonow.exception.PersonNotFoundException;
import com.bead.rentonow.front.ApiResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PropertyService {

    ApiResponse<List<PropertyDto>> read();
    ApiResponse<List<PropertyDto>> readWithFilters(String location, BigDecimal price, LocalDate startDate, LocalDate endDate);
    ApiResponse<PropertyInfoDto> read(Long id);
    ApiResponse<PropertyInfoDto> create(PropertyInfoDto propertyInfoDto, Long personId) throws PersonNotFoundException;
}
