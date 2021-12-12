package com.bead.rentonow.controller;

import com.bead.rentonow.service.PersonService;
import com.bead.rentonow.service.PropertyService;
import com.bead.rentonow.service.BookingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentoNowController {

    private PersonService userService;
    private PropertyService propertyService;
    private BookingService bookingService;

    // Constructor
    public RentoNowController(PersonService userService, PropertyService propertyService, BookingService bookingService) {
        this.userService = userService;
        this.propertyService = propertyService;
        this.bookingService = bookingService;
    }
}