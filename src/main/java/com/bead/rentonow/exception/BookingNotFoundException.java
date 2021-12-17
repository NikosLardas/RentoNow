package com.bead.rentonow.exception;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String description) {
        super(description);
    }
}
