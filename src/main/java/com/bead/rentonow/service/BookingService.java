package com.bead.rentonow.service;

import com.bead.rentonow.dto.BookingDto;
import com.bead.rentonow.exception.PersonNotFoundException;
import com.bead.rentonow.exception.PropertyNotFoundException;
import com.bead.rentonow.front.ApiResponse;
import java.util.List;

public interface BookingService {

    ApiResponse<List<BookingDto>> read();
    ApiResponse<BookingDto> read(Long id);
    ApiResponse<BookingDto> create(BookingDto bookingDto, Long propertyId, Long personId) throws PropertyNotFoundException, PersonNotFoundException;
}
