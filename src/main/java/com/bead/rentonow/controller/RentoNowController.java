package com.bead.rentonow.controller;

import com.bead.rentonow.service.UserService;
import com.bead.rentonow.service.PropertyService;
import com.bead.rentonow.service.BookingService;
import com.bead.rentonow.service.PaymentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentoNowController {

    private UserService userService;
    private PropertyService propertyService;
    private BookingService bookingService;
    private PaymentService paymentService;

    // Constructor
    public RentoNowController(UserService userService, PropertyService propertyService, BookingService bookingService, PaymentService paymentService) {
        this.userService = userService;
        this.propertyService = propertyService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }
}
