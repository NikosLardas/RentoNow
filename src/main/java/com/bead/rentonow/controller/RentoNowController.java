package com.bead.rentonow.controller;

import com.bead.rentonow.dto.*;
import com.bead.rentonow.exception.PersonNotFoundException;
import com.bead.rentonow.exception.PropertyNotFoundException;
import com.bead.rentonow.front.ApiResponse;
import com.bead.rentonow.service.PersonService;
import com.bead.rentonow.service.PropertyService;
import com.bead.rentonow.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentoNowController {

    private PersonService personService;
    private PropertyService propertyService;
    private BookingService bookingService;

    public RentoNowController(PersonService personService, PropertyService propertyService, BookingService bookingService) {
        this.personService = personService;
        this.propertyService = propertyService;
        this.bookingService = bookingService;
    }

    @GetMapping("ping")
    public String ping() {
        return "The API is working";
    }

    @GetMapping("property")
    public ApiResponse<List<PropertyDto>> getProperties() {

        return propertyService.read();
    }

    @GetMapping("property/{id}")
    public ApiResponse<PropertyInfoDto> getProperty(@PathVariable long id) {

        return propertyService.read(id);
    }

    @PostMapping("property/{personId}")
    public ApiResponse<PropertyInfoDto> createProperty(@PathVariable long personId,@RequestBody PropertyInfoDto property)
    throws PersonNotFoundException{

        return propertyService.create(property,personId);
    }

    ////

    @GetMapping("person")
    public ApiResponse<List<PersonDto>> getPeople() {

        return personService.read();
    }

    @PostMapping("person")
    public ApiResponse<PersonDto> createPerson(@RequestBody PersonDto person) {

        return personService.create(person);
    }

    ////

    @GetMapping("booking")
    public ApiResponse<List<BookingDto>> getBookings() {

        return bookingService.read();
    }

    @GetMapping("booking/{id}")
    public ApiResponse<BookingDto> getBooking(@PathVariable long id) {

        return bookingService.read(id);
    }

    @PostMapping("booking/property/{propertyId}/person/{personId}")
    public ApiResponse<BookingDto> createBooking(@PathVariable long propertyId,@PathVariable long personId,@RequestBody BookingDto booking)
    throws PropertyNotFoundException,PersonNotFoundException{

        return bookingService.create(booking,propertyId,personId);
    }

    @DeleteMapping("booking/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        return bookingService.delete(id);
    }

    @GetMapping("statistics/{type}")
    public ApiResponse<List<StatisticsDto>> getStatistics(@PathVariable String type) {

        return bookingService.getStatistics(type);
    }
}