package com.bead.rentonow.service;

import com.bead.rentonow.dto.BookingDto;
import com.bead.rentonow.exception.PersonNotFoundException;
import com.bead.rentonow.exception.PropertyNotFoundException;
import com.bead.rentonow.front.ApiResponse;
import com.bead.rentonow.model.Booking;
import com.bead.rentonow.model.Property;
import com.bead.rentonow.model.Person;
import com.bead.rentonow.repository.BookingRepository;
import com.bead.rentonow.repository.PersonRepository;
import com.bead.rentonow.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    PropertyRepository propertyRepository;
    PersonRepository personRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, PropertyRepository propertyRepository, PersonRepository personRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.personRepository = personRepository;
    }

    // Get all bookings
    @Override
    public ApiResponse<List<BookingDto>> read() {
        return new ApiResponse<List<BookingDto>>(200, "ok", bookingRepository
                .findAll()
                .stream()
                .map(BookingDto::new)
                .collect(Collectors.toList()));
    }

    // Get one booking
    @Override
    public ApiResponse<BookingDto> read(Long id) {
        Optional<Booking> oBooking = bookingRepository.findById(id);
        if (oBooking.isPresent())
            return new ApiResponse<BookingDto>(201,"ok",
                    new BookingDto(oBooking.get()));
        else
            return new ApiResponse<BookingDto>(400, "not found", null);
    }

    // Create one booking
    @Override
    public ApiResponse<BookingDto> create(BookingDto bookingDto, Long propertyId, Long personId) throws PropertyNotFoundException, PersonNotFoundException {
        Optional<Property> oProperty = propertyRepository.findById(propertyId);
        Optional<Person> oPerson = personRepository.findById(personId);

        if (!oProperty.isPresent())
            throw new PropertyNotFoundException("Property with ID: "+propertyId +" not found !");
        else if (!oPerson.isPresent())
            throw new PersonNotFoundException("Person with ID: "+personId +" not found !");

        Booking booking = bookingDto.getBooking();
        booking.setProperty(oProperty.get());
        booking.setGuest(oPerson.get());

        return new ApiResponse<BookingDto>(201,"ok",
                new BookingDto(bookingRepository.save(booking)));

    }
}
