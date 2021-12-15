package com.bead.rentonow.dto;

import com.bead.rentonow.model.Booking;
import com.bead.rentonow.model.Person;
import com.bead.rentonow.model.Property;
import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {

    private Long id;
    private Date bookingStartDate;
    private Date bookingEndDate;
    private int isPaid;
    private String propertyTitle;
    private String guestName;

    public BookingDto(Booking booking, Property property, Person person) {
        id = booking.getId();
        bookingStartDate = booking.getBookingStartDate();
        bookingEndDate = booking.getBookingEndDate();
        isPaid = booking.getIsPaid();
        propertyTitle = property.getTitle();
        guestName = person.getFullName();
    }

    //mappings
    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setBookingStartDate(bookingStartDate);
        booking.setBookingEndDate(bookingEndDate);
        booking.setIsPaid(isPaid);
        booking.getProperty().setTitle(propertyTitle);      // Missing data. Need to Re-check
        booking.getGuest().setFullName(guestName);          // Missing data. Need to Re-check

        return booking;
    }
}
